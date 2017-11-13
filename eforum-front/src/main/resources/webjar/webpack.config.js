var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
	devtool: 'source-map',//配置生成Source Maps，选择合适的选项
	entry: {
        main: path.join(__dirname, 'src', 'main.js'),
        vendor: ['angular', 'angular-route']
    },
    output: {
        path: path.join(__dirname, '..', 'public', 'dist'),
        filename: 'bundle.[hash].js'
    },
    module: {
    	loaders: [
    		{test: /\.css$/, loader: 'style-loader!css-loader'},
    		{test: /\.html$/, loader: 'html-loader'},
    		{test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'},
    		{test: /\.(eot|woff|woff2|svg|ttf)([\?]?.*)$/, loader: "file-loader?name=[name].[ext]&publicPath=dist/"}
    	]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            filename: 'vendor.[hash].js'
        }),
        new HtmlWebpackPlugin({
        	inject: 'head',
        	filename: '../index.html',
            template: 'template.html'
        })
    ]
};