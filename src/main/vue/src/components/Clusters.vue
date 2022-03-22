<script>
import http from "../services/http";

export default {
  data() {
    return {
      clusters: []
    }
  },
  mounted() {
    http
        .get("/clusters")
        .then(response => (this.clusters = response.data));
  }
}
</script>

<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Clusters</h1>
  </div>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">URL</th>
      <th scope="col">Version</th>
      <th scope="col">Running</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="cluster in clusters" :class="cluster.running ? 'table-success' : 'table-danger'">
      <th scope="row"><router-link :to="'/clusters/' + cluster.name + '/connectors'">{{ cluster.name }}</router-link></th>
      <td>{{ cluster.url }}</td>
      <td>{{ cluster.version }}</td>
      <td>{{ cluster.running }}</td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>
</style>
