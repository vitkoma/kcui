import Clusters from '../components/Clusters.vue'
import Connectors from '../components/Connectors.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    { path: '/', name: 'clusters', component: Clusters },
    { path: '/clusters/:clusterName/connectors', name: 'connectors', component: Connectors },
]

const router = createRouter({
    history: createWebHistory('/'),
    routes
})

export default router
