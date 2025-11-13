package main

import (
	"context"

	"example/my-first-provider/myprovider"

	"github.com/hashicorp/terraform-plugin-framework/providerserver"
)

func main() {
	providerserver.Serve(context.Background(), myprovider.New, providerserver.ServeOpts{
		Address: "registry.local/hashicorp/myprovider",
	})
}
