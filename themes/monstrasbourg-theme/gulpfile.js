'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var plugins = require('gulp-load-plugins')({
    rename: {
        'gulp-clean-css': 'cleancss',
        'gulp-sass-import-json': 'jsonToSass',
        'gulp-ruby-sass': 'rubysass',
        'gulp-require-tasks': 'requireTask',
        'gulp-ext-replace': 'extReplace',
        'gulp-sass-glob': 'globs'
    }
});
var rename = require('gulp-rename');
var globSass = require('gulp-sass-glob-import');
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


gulp.task('css', function() {
  return gulp.src('./custom/strasbourg.scss')
    .pipe(plugins.sourcemaps.init())
    .pipe(globSass())
    .pipe(plugins.plumber({
        errorHandler: function (err) {
            console.log(err);
            this.emit('end');
        }
    })) 
    .pipe(plugins.sass({
      sourceComments: 'map'
    }))
    .pipe(plugins.sourcemaps.write('.'))
    .pipe(gulp.dest('./src/css/'))
});

gulp.task('js', function(){
    return gulp.src('./custom/strasbourg.js')
        //.pipe(plugins.sourcemaps.init())
        .pipe(plugins.plumber())
        .pipe(plugins.include())
        .on('error', console.log)
        //.pipe(plugins.sourcemaps.write('.'))
        .pipe(gulp.dest('src/js/'));
});