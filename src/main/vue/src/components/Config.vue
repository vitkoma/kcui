<script>
import connectorService from "../services/connectors";

export default {
  data() {
    return {
      loading: true,
      config: {}
    }
  },
  mounted() {
    reload(this)
  },
  methods: {
    reload() {
      reload(this)
    }
  }
}

const reload = (data) => {
  data.loading = true;
  connectorService.fetchConfig(data.$route.params.clusterName, data.$route.params.connectorName,(response) => {
    data.config = response.data
    data.loading = false;
  })
}

</script>


<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Connector configuration [{{ $route.params.clusterName }} / {{ $route.params.connectorName }}]</h1>
  </div>
  <div class="pt-3">
    <div class="text-end">
      <button @click="reload" type="button" class="btn btn-primary me-2">
        <span v-if="loading" class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
        {{loading ? "Loading..." : "Reload"}}
      </button>
    </div>
  </div>
  <div class="table-responsive">
    <table class="table table-striped table-sm">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Value</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(value, property) in config">
        <th>{{ property }}</th>
        <td>{{ value }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<style scoped>
</style>
