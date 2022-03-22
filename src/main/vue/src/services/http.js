import axios from 'axios'

const http = axios.create({
    baseURL: import.meta.env.VITE_KCUI_API_BASE_URL,
    timeout: 30000,
    headers: {'content-type': 'application/json'}
});

export default http;