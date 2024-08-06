import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    outDir: './dist'
  },
  base: "/ui/",
  plugins: [vue()],
  server: {
    proxy: {
      port: '3000',
      base: "./", //生产环境路径
      "/api": {
        target: "http://localhost:8888",
        changeOrigin: true,
        //rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },

})
