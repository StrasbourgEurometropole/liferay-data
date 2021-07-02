'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var plugins = require('gulp-load-plugins')();
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
    var source = './custom/css/';
    var destination = './src/css/';

    return gulp.src(source + '/style.scss')
    .pipe(plugins.sass({outputStyle: 'compressed'}))
    .pipe(gulp.dest(destination));
});

gulp.task('js', function () {
    var source = './custom/js/';
    var destination = './src/js/';

    return gulp.src([source + 'libs/*.js', source + 'src/*.js'])
    .pipe(plugins.concat('./t_main.js'))
    .pipe(gulp.dest(destination + './'))
    .pipe(plugins.uglify())
    .pipe(plugins.rename({suffix: '.min'}))
    .pipe(gulp.dest(destination));
});
