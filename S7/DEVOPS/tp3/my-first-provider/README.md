# Terraform Provider Simple

Un provider Terraform minimal qui expose une seule ressource `simple_resource`.

## Exemple d’utilisation

```hcl
terraform {
  required_providers {
    simple = {
      source = "registry.local/hashicorp/simple"
      version = "0.1.0"
    }
  }
}

provider "simple" {}

resource "simple_resource" "example" {
  name = "hello"
}
