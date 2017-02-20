module.exports = {
    entry: "./gmedia.js",
    output: {
        filename: "./dist/bundle.js"
    },
    
    devtool: "source-map",
    
    resolve: {
        extensions: ["", ".webpack.js", ".web.js", ".ts", ".tsx", ".js"] 
    },
    
    module: {
        loaders: [
            { 
	      test: /\.tsx?$/, 
	      loader: "ts-loader",
	      query: { 
	      	presets: ['es2015', 'stage-0'] 
	      }
	    }
        ],
        
        preLoaders: [
            { 
	      test: /\.js$/, 
	      loader: "source-map-loader" 
	    }
        ]
    }
};
