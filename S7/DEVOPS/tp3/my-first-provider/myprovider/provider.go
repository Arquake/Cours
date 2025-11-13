package myprovider

import (
	"context"

	"github.com/hashicorp/terraform-plugin-framework/datasource"
	"github.com/hashicorp/terraform-plugin-framework/provider"
	"github.com/hashicorp/terraform-plugin-framework/resource"

)

var _ provider.Provider = &MyProvider{}

type MyProvider struct{}

func New() provider.Provider {
	return &MyProvider{}
}

func (p *MyProvider) Metadata(_ context.Context, _ provider.MetadataRequest, resp *provider.MetadataResponse) {
	resp.TypeName = "myprovider"
}

func (p *MyProvider) Schema(_ context.Context, _ provider.SchemaRequest, resp *provider.SchemaResponse) {}

func (p *MyProvider) Configure(_ context.Context, _ provider.ConfigureRequest, _ *provider.ConfigureResponse) {}

func (p *MyProvider) Resources(_ context.Context) []func() resource.Resource {
	return []func() resource.Resource{
		NewSimpleResource,
	}
}

func (p *MyProvider) DataSources(_ context.Context) []func() datasource.DataSource {
	return nil
}
