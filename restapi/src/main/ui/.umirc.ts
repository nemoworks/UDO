import { defineConfig } from 'umi'

export default defineConfig({
  nodeModulesTransform: {
    type: 'none',
  },
  title: 'Universal Digital Object',
  favicon: 'favicon.png',
  sass: {},
  dva: {},
  locale: {},
  antd: {},
  proxy: {
    '/api': {
      target: 'http://192.168.80.129:8000',
      changeOrigin: true,
      secure: false,
      // pathRewrite: { '^/api': '' },
    },
  },
})
