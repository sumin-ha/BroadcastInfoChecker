module.exports = {
  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: false
    }
  },
  transpileDependencies: [
    'quasar'
  ],
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    port: 9091
    , proxy: "http://localhost:9191"
  },
  chainWebpack: config=> {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader")
  }
//  presets: [
//    '@vue/cli-plugin-babel/preset'
//  ]
}
