<script>
import connectorService from "../services/connectors";

export default {
  data() {
    return {
      loading: true,
      connectors: [],

      stateClass: (state) => {
        if (state === 'RUNNING') return 'bg-success';
        else if (state === 'FAILED') return 'bg-danger';
        else return 'bg-secondary';
      },
      tasksStatus: (tasks) => {
        return tasks.filter((el) => el.state === 'RUNNING').length + '/' +  tasks.length;
      },
      tasksStateClass: (tasks) => {
        return tasks.filter((el) => el.state !== 'RUNNING').length === 0 ? 'bg-success' : 'bg-danger';
      },
      pauseEnabled: (connectors) => {
        return connectors.some((el) => el.selected && el.connector.state === 'RUNNING')
      },
      resumeEnabled: (connectors) => {
        return connectors.some((el) => el.selected && el.connector.state === 'PAUSED')
      },
      someSelected: (connectors) => {
        return connectors.some((el) => el.selected)
      }
    }
  },
  mounted() {
    reload(this)
  },
  methods: {
    reload() {
      reload(this)
    },
    restart() {
      restartConnectors(this)
    },
    pause() {
      pauseConnectors(this)
    },
    resume() {
      resumeConnectors(this)
    },
    deleteConn() {
      deleteConnectors(this)
    }
  }
}

const selectedNames = (data) => {
  return data.connectors.filter(el => el.selected).map((el =>el.name))
}

const createCallback = (data) => {
  return (response) => {
    response.data.forEach( newConn => {
      let idx = data.connectors.findIndex( oldConn => oldConn.name === newConn.name)
      data.connectors[idx] = newConn
    })
    data.loading = false
  }
}

const reload = (data) => {
  data.loading = true;
  connectorService.listConnectors(data.$route.params.clusterName, (response) => {
    response.data.sort((c1, c2) => c1.name.localeCompare(c2))
    data.connectors = response.data.sort((c1, c2) => c1.name.localeCompare(c2))
    data.loading = false;
  })
}

const restartConnectors = (data) => {
  data.loading = true;
  connectorService.restartConnectors(data.$route.params.clusterName, selectedNames(data), createCallback(data))
}

const pauseConnectors = (data) => {
  data.loading = true;
  connectorService.pauseConnectors(data.$route.params.clusterName, selectedNames(data), createCallback(data))
}

const resumeConnectors = (data) => {
  data.loading = true;
  connectorService.resumeConnectors(data.$route.params.clusterName, selectedNames(data), createCallback(data))
}

const deleteConnectors = (data) => {
  data.loading = true;
  connectorService.deleteConnectors(data.$route.params.clusterName, selectedNames(data), createCallback(data))
}

</script>


<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Connectors [{{ $route.params.clusterName }}]</h1>
  </div>
  <div class="pt-3">
    <div class="text-end">
      <button @click="reload" type="button" class="btn btn-primary me-2">
        <span v-if="loading" class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
        {{loading ? "Loading..." : "Reload"}}
      </button>
      <span class="me-2">{{connectors.filter(el => el.selected).length}} selected</span>
      <button @click="restart" type="button" class="btn btn-info me-2" :disabled="! someSelected(connectors)">
        Restart
      </button>
      <button @click="pause" type="button" class="btn btn-warning me-2" :disabled="! pauseEnabled(connectors)">
        Pause
      </button>
      <button @click="resume" type="button" class="btn btn-success me-2" :disabled="! resumeEnabled(connectors)">
        Resume
      </button>
      <button data-bs-toggle="modal" data-bs-target="#exampleModal" type="button" class="btn btn-danger me-2" :disabled="! someSelected(connectors)">
        Delete
      </button>
    </div>
  </div>
  <div class="table-responsive">
    <table class="table table-striped table-sm">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Type</th>
        <th scope="col">Name</th>
        <th scope="col">Status</th>
        <th scope="col">Tasks</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="connector in connectors">
        <td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" v-model="connector.selected"></td>
        <td>{{ connector.type }}</td>
        <td>{{ connector.name }}</td>
        <td><span class="badge" :class="stateClass(connector.connector.state)">{{ connector.connector.state }}</span></td>
        <td><span class="badge" :class="tasksStateClass(connector.tasks)">{{ tasksStatus(connector.tasks) }}</span></td>
      </tr>
      </tbody>
    </table>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Are You Sure?</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Do you want to delete {{connectors.filter(el => el.selected).length}} connector(s)?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
          <button @click="deleteConn" type="button" class="btn btn-danger" data-bs-dismiss="modal">Yes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
