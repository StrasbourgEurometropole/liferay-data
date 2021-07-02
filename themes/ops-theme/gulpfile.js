'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var plugins = require('gulp-load-plugins')();

var phpinc = require("php-include-html");
var del = require('del');
var runSequence = require('run-sequence').use(gulp);

liferayThemeTasks.registerTasks({
  gulp: gulp,
  hookFn: function(gulp) {

        gulp.hook('after:build:move-compiled-css', function(done) {
            runSequence('remove-maps', done);
        })
  }
});

gulp.task('remove-maps', cb => {
	del('./build/css/*.map').then(() => cb());
});

gulp.task('css', function () {
    return gulp.src('./custom/scss/ops.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(gulp.dest('./src/css/'));
});

gulp.task('js', function () {
    setTimeout(function() {
        return gulp.src(['./custom/js/config.js','./custom/js/global.js','./custom/js/libs/jquery.js','./custom/js/libs/jquery*.js','./custom/js/libs/leaflet/leaflet.js','./custom/js/libs/leaflet/mapbox-*.js','./custom/js/libs/leaflet/leaflet-*.js','./custom/js/libs/leaflet/th_maps-leaflet.js','./custom/js/libs/*.js','./custom/js/src/*.js','./custom/js/src-1/*.js','./custom/js/src-2/*.js','./custom/js/src-3/*.js','./custom/js/override/*.js'])
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
    gulp.src(['./custom/js/config.js','./custom/js/global.js','./custom/js/libs/jquery.js','./custom/js/libs/jquery*.js','./custom/js/libs/leaflet/leaflet.js','./custom/js/libs/leaflet/mapbox-*.js','./custom/js/libs/leaflet/leaflet-*.js','./custom/js/libs/leaflet/th_maps-leaflet.js','./custom/js/libs/*.js','./custom/js/src/*.js','./custom/js/src-1/*.js','./custom/js/src-2/*.js','./custom/js/src-3/*.js'])
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