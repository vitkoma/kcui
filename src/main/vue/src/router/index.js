import Clusters from '../components/Clusters.vue'
import Connectors from '../components/Connectors.vue'
import Config from "../components/Config.vue";
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    { path: '/', name: 'clusters', component: Clusters },
    { path: '/clusters/:clusterName/connectors', name: 'connectors', component: Connectors },
    { path: '/clusters/:clusterName/connectors/:connectorName/config', name: 'connectorConfig', component: Config },
]

const router = createRouter({
    history: createWebHistory('/'),
    routes
})

export default router
