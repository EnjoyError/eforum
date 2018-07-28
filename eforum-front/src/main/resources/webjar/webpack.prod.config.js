const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const QiniuPlugin = require('qiniu-webpack-plugin');

const Ak_QN = "";               // ACCESS_KEY
const Sk_QN = "";               // SECRET_KEY
const PATH_QN = "eforum";       // 上传到存储空间的某个文件夹
const BUCKET_QN = "demo";       // 存储空间名称
const PUBLIC_PATH = "http://res.stustyle.cn/eforum";    // 公共目录

module.exports = {
	entry: {
        main: path.join(__dirname, 'src', 'main.js'),
        vendor: ['angular', 'angular-route']
    },
    output: {
        publicPath:PUBLIC_PATH,                             // 七牛云存储空间
        filename: 'bundle.js',
        path: path.join(__dirname, '..', 'public', 'dist')
    },
    module: {
    	loaders: [
    		{test: /\.css$/, loader: 'style-loader!css-loader'},
    		{test: /\.html$/, loader: 'html-loader'},
    		{test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'},
    		{test: /\.(eot|woff|woff2|svg|ttf)([\?]?.*)$/, loader: "file-loader?name=[name].[ext]"}
    	]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            filename: 'vendor.js'
        }),
        new HtmlWebpackPlugin({
        	inject: 'head',
        	filename: '../index.html',
            template: 'template.html'
        }),
        // 七牛云存储插件
        new QiniuPlugin({
            ACCESS_KEY: Ak_QN,                          //AK
            SECRET_KEY: Sk_QN,                          //SK
            bucket: BUCKET_QN,                                                 // 存储空间名称
            include:[/\.(png|jpg|gif|css|eot|woff|woff2|svg|ttf|js)$/],     // 上传文件
            path: PATH_QN                                                  // 上传到存储空间的某个文件夹
        })
    ]
};