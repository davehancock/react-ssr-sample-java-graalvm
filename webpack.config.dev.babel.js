const path = require('path');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');

module.exports = {
    mode: 'development',
    entry: './react-src/client.js',
    output: {
        filename: '[name].js',
    },

    devServer: {
        contentBase: path.join(__dirname, 'public'),
        hot: true
    },

    devtool: 'inline-source-map',

    plugins: [
        new HtmlWebpackPlugin({template: './public/index.html'}),
        new webpack.HotModuleReplacementPlugin()
    ],

    module: {
        rules: [
            {
                test: /\.scss$/,
                use: [
                    "style-loader",
                    "css-loader",
                    "sass-loader"
                ]
            },
            {
                test: /\.jpg$/,
                use: 'file-loader'
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: 'babel-loader',
            },
        ],
    },
};
