#!/bin/bash
declare dc_infra="docker-compose-infra.yml"
declare dc_server="docker-compose-server.yml"
declare dc_api="docker-compose-api.yml"

function init() {
	echo "Initializing..."
	echo "Creating docker network with name: ism"
	create_network "ism"
	copy_init_db "classroom"
	copy_init_db "student"
	copy_init_db "user"
	cp local.env .env
}

function create_network() {
    local network_name="$1"
    if ! docker network inspect "$network_name" >/dev/null 2>&1; then
			docker network create "$network_name" --driver=bridge
    else
			echo "Network $network_name already exists. Skipping creation."
    fi
}

function copy_init_db() {
    local service_name="$1"
    local dest_dir=".docker/schema/$service_name"
    local pg_data_dir=".docker/pg_${service_name}_data"

		if [[ -d "$pg_data_dir" ]]; then
			echo "Volume directory '$pg_data_dir' already exists, removing existing directory..."
			sudo rm -rf "$pg_data_dir"
		fi

    if [[ -d "$dest_dir" ]]; then
			echo "docker dir already exist, remove existing directory"
      sudo rm -rf "$dest_dir"
    fi
    mkdir -p ".docker/schema/$service_name"
    cp "schema/$service_name/init-db.sql" ".docker/schema/$service_name"
}

function build() {
  cd "$1" && mvn package -DskipTests && cd ..
}

function clean_project() {
  cd "$1" && mvn clean && cd ..
}


function build_all() {
	build "configapp"
	build "discoveryserver"
	build "gateway"
	build "classroom"
	build "student"
	build "user"
}

function teardown(){
	clean_project "configapp"
	clean_project "discoveryserver"
	clean_project "gateway"
	clean_project "classroom"
	clean_project "student"
	clean_project "user"
	sudo rm -rf .docker
}

function stop(){
	echo "Stopping all docker containers...."
	docker compose -f ${dc_infra} -f ${dc_server} -f ${dc_api} stop
	docker compose -f ${dc_infra} -f ${dc_server} -f ${dc_api} rm -f
}

function start() {
	echo "Starting microservices using docker compose..."

	read -p "Is this the initial setup? If so, initialization procedures will be executed (y/n): " init_choice
	if [[ "$init_choice" == "y" || "$init_choice" == "Y" ]]; then
		init
	fi

	read -p "Do you want to rebuild the project? (y/n): " rebuild_choice
	if [[ "$rebuild_choice" == "y" || "$rebuild_choice" == "Y" ]]; then
		build_all
	fi

	echo "Starting containers"
	docker compose -f ${dc_server} -f ${dc_infra} -f ${dc_api} --env-file .env up --build -d
	# docker compose -f ${dc_server} -f ${dc_infra} --env-file .env up --build -d
}

function restart() {
	echo "Restarting containers..."
	stop
	start
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}


