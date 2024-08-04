// import {VUE_APP_BASE_CONFIG} from "../config/config"
import axios from 'axios'
import { AxiosResponse } from 'axios';


const service = axios.create({
  // baseURL: VUE_APP_BASE_CONFIG.baseUrl,
  timeout: 60000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 添加请求头等前置处理
    config.headers['Authorization'] = 'Bearer' + ' ' + localStorage.getItem('token')
    return config
  },
  error => {
    // 请求错误处理
    console.log('Request Error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    // 响应后处理
    let httpResponse: AxiosResponse<Response>;
    if (response.status === 200 && response.data.code === 200) {
        httpResponse = response
      return Promise.resolve(httpResponse)
    } else {
      httpResponse = response
      return Promise.reject(httpResponse)
    }
  },
  error => {
    console.log('Response Error:', error)
    return Promise.reject(error)
  }
)

export default service