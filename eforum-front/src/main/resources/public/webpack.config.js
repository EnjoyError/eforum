var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: {
        main: path.join(__dirname, 'src', 'main.js'),
        vendor: ['angular', 'angular-route']
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    module: {
    	loaders: [
    		{test: /\.css$/, loader: 'style-loader!css-loader'},
    		{test: /\.html$/, loader: 'html-loader'},
    		{test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'},
    		{test: /\.(eot|woff|woff2|svg|ttf)([\?]?.*)$/, loader: "file-loader"}
    	]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            filename: 'vendor.js'
        })
    ]
};