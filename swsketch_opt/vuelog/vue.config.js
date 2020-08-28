const devConfig = {
  devtool: 'source-map',
  externals: {
    database: 'SwSketchbook.database'
  },
  entry: {
    app: './src/main.js',
    style: [
      'bootstrap/dist/css/bootstrap.min.css',
      'bootstrap/dist/js/bootstrap.min.js',
      'sweetalert2/dist/sweetalert2.min.css'
    ]
  }
}

module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/rt/*': {
        target: 'http://localhost:8080'
      }
    }
  },
  configureWebpack: devConfig,
  outputDir: 'docs',
  publicPath: '/',
  css: {
    extract: false
  }
}
