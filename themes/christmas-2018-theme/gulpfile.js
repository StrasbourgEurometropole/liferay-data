'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var plugins = require('gulp-load-plugins')();
var del = require('del');

liferayThemeTasks.registerTasks({
	gulp: gulp
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
