terraform {
    required_providers {
        myprovider = {
            source = "registry.local/hashicorp/myprovider"
            version = "0.1.0"
        }
    }   
}
provider "myprovider" {}

resource "myprovider_simple_resource" "demo" {
    name = "hello-world"
}