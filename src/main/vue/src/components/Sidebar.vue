<script setup>
defineProps({
  cluster: {
    type: String,
    required: true
  }
})
</script>

<template>
  <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="nav flex-column">
        <li class="nav-item">
          <router-link :to="{ name: 'clusters'}"
                       class="nav-link" :class="$route.name === 'clusters' ? 'active' : ''"
                       :aria-current="$route.name === 'clusters' ? 'page' : undefined">
            Clusters
          </router-link>
        </li>
        <li class="nav-item" v-if="$route.params.clusterName">
          <router-link :to="{ name: 'connectors', params: { clusterName: $route.params.clusterName }}"
                       class="nav-link" :class="$route.name === 'connectors' ? 'active' : ''"
                       :aria-current="$route.name === 'connectors' ? 'page' : undefined">
            Connectors
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<style scoped>

.sidebar {
  position: fixed;
  top: 0;
  /* rtl:raw:
  right: 0;
  */
  bottom: 0;
  /* rtl:remove */
  left: 0;
  z-index: 100; /* Behind the navbar */
  padding: 48px 0 0; /* Height of navbar */
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
}

@media (max-width: 767.98px) {
  .sidebar {
    top: 5rem;
  }
}

.sidebar-sticky {
  position: relative;
  top: 0;
  height: calc(100vh - 48px);
  padding-top: .5rem;
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
}

.sidebar .nav-link {
  font-weight: 500;
  color: #333;
}

.sidebar .nav-link.active {
  color: #2470dc;
}

.sidebar-heading {
  font-size: .75rem;
  text-transform: uppercase;
}

</style>
