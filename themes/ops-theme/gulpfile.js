'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var plugins = require('gulp-load-plugins')();

var phpinc = require("php-include-html");

var autoprefixer_options = {
    browsers: ['> 1%','last 4 versions','ios > 7','android > 4','chrome > 18','ff > 10','opera > 1','safari > 5', 'ie > 7']
};

liferayThemeTasks.registerTasks({
	gulp: gulp,
});

gulp.task('css', function () {
    return gulp.src('./custom/scss/ops.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(plugins.autoprefixer(autoprefixer_options))
        .pipe(gulp.dest('./src/css/'));
});

gulp.task('js', function () {
    setTimeout(function() {
        return gulp.src(['./custom/js/config.js','./custom/js/global.js','./custom/js/libs/jquery.js','./custom/js/libs/jquery*.js','./custom/js/libs/leaflet/leaflet.js','./custom/js/libs/leaflet/mapbox-*.js','./custom/js/libs/leaflet/leaflet-*.js','./custom/js/libs/leaflet/th_maps-leaflet.js','./custom/js/libs/tarteaucitron/tarteaucitron.js','./custom/js/libs/tarteaucitron/*.js','./custom/js/libs/*.js','./custom/js/src/*.js','./custom/js/src-1/*.js','./custom/js/src-2/*.js','./custom/js/src-3/*.js'])
            .pipe(plugins.concat('ops.js'))
            .on('error', function (err) {
                console.log(err.toString());
                this.emit('end');
            })
            //.pipe(gulp.dest(destination + '_js/'))
            //.pipe(plugins.uglify())
            //.on('error', function (err) {
            //    console.log(err.toString());
            //    this.emit('end');
            //})
            .pipe(gulp.dest('src/js/'));
        //.pipe(plugins.uglify())
        //.pipe(plugins.rename({suffix: '.min'}))
        //.pipe(gulp.dest('./dist/'));
    },1000);
});

gulp.task('toprod', function () {
    gulp.src(['./custom/js/config.js','./custom/js/global.js','./custom/js/libs/jquery.js','./custom/js/libs/jquery*.js','./custom/js/libs/leaflet/leaflet.js','./custom/js/libs/leaflet/mapbox-*.js','./custom/js/libs/leaflet/leaflet-*.js','./custom/js/libs/leaflet/th_maps-leaflet.js','./custom/js/libs/tarteaucitron/tarteaucitron.js','./custom/js/libs/tarteaucitron/*.js','./custom/js/libs/*.js','./custom/js/src/*.js','./custom/js/src-1/*.js','./custom/js/src-2/*.js','./custom/js/src-3/*.js'])
        .pipe(plugins.concat('ops.js'))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        // .pipe(gulp.dest(destination + '_js/'))
        .pipe(gulp.dest('./dist/'))
        .pipe(plugins.uglify())
        .pipe(plugins.rename({suffix: '.min'}))
        .pipe(gulp.dest('./dist/'));

    gulp.src(['!./js/libs/jquery.js','./js/libs/jquery*.js','./js/libs/*.js','./js/src-1/*.js','./js/src-2/*.js','./js/src-3/*.js','./js/**/*.js','./js/*.js'])
        .pipe(plugins.concat('main-panier.js'))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        // .pipe(gulp.dest(destination + '_js/'))
        .pipe(gulp.dest('./dist/'))
        .pipe(plugins.uglify())
        .pipe(plugins.rename({suffix: '.min'}))
        .pipe(gulp.dest('./dist/'));

    gulp.src('./scss/style.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(plugins.autoprefixer(autoprefixer_options))
        .pipe(gulp.dest('./dist/'));

    return true;
});

gulp.task('default', ['watch']);