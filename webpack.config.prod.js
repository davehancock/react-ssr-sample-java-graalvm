const path = require('path');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const SERVER_OUTPUT_PATH = 'src/main/resources/public';

module.exports = [
    // Client (frontend) bundle
    {
        mode: 'production',
        entry: './react-src/client.js',

        output: {
            filename: 'client.[chunkhash:8].js',
            path: path.resolve(__dirname, SERVER_OUTPUT_PATH),
        },

        devtool: 'source-map',

        plugins: [
            new CleanWebpackPlugin([SERVER_OUTPUT_PATH]),
            new HtmlWebpackPlugin({template: './public/index.html'}),
            new MiniCssExtractPlugin()
        ],

        module: {
            rules: [
                {
                    test: /\.scss$/,
                    use: [
                        MiniCssExtractPlugin.loader,
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
    },
    // Server side (SSR) bundle
    {
        mode: 'production',
        entry: './react-src/server.js',

        output: {
            filename: 'server.js',
            path: path.resolve(__dirname, SERVER_OUTPUT_PATH),
        },

        plugins: [
            new CleanWebpackPlugin([SERVER_OUTPUT_PATH]),
        ],

        module: {
            rules: [
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
    },
];
