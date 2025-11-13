terraform {
    required_providers {
        docker = {
            source="kreuzwerker/docker"
            version = "3.0.2"
        }
    }
}

provider "docker" {
  
}

resource "docker_image" "redis"{
    name = "docker.io/redis:6.0.5"
}

resource "docker_container" "db"{
    name = "redis_db"
    image = docker_image.redis.image_id
}

resource "docker_image" "guestbook-frontend" {
    name = "gb-frontend"
    build {
        context = "./gb-frontend/"
    }
}

resource "docker_container" "frontend" {
    name = "guestbook_frontend"
    image = docker_image.guestbook-frontend.image_id
    ports {
    internal = "80"
    external = "8080"
    }
        env = [
            "GET_HOSTS_FROM=dns"
        ]
    host {
        host = "redis-leader"
        ip = docker_container.db.network_data[0].ip_address
    }
    host {
        host = "redis-follower"
        ip = docker_container.db.network_data[0].ip_address
    }
}

output "app_endpoint" {
    value = "http://localhost:${docker_container.frontend.ports[0].external}"
    description = "The URL endpoint to access the Guestbook application"
}