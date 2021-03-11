import { defineConfig } from 'umi';

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
    // '/cordra': {
    //   target: 'https://192.168.224.1:7711',
    //   changeOrigin: true,
    //   secure: false,
    //   pathRewrite: { '^/cordra': '' },
    // },
  },
});
