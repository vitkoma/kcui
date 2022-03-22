<script setup>
import Sidebar from './components/Sidebar.vue'
</script>

<template>

  <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">KCUI - Kafka Connect UI</a>
  </header>

  <div class="container-fluid">
    <div class="row">

      <Sidebar :cluster="$route.params.clusterName"/>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

        <router-view></router-view>

      </main>
    </div>
  </div>

</template>

<style>

</style>