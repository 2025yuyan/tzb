import { defineConfig } from 'vite'
import uni from '@dcloudio/vite-plugin-uni'

export default defineConfig({
  base: '/tzb/',
  plugins: [
    uni({
      h5: {
        publicPath: '/tzb/'
      }
    }),
  ],
  server: {
    proxy: {
      '/api': {
        target: 'https://tiiaozhanbei.vercel.app',
        changeOrigin: true,
      }
    }
  }
})