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
var del = require('del');

liferayThemeTasks.registerTasks({
	gulp: gulp
});


gulp.task('css', function () {
    var source = './custom/css/';
    var destination = './src/css/';
    var autoprefixer_options = {
        browsers: ["last 3 versions", "> 5%", "not explorer <= 8"]
    };

    return gulp.src(source + '/t_main.scss')
    .pipe(plugins.sass({outputStyle: 'compressed'}))
   // .pipe(plugins.autoprefixer(autoprefixer_options))
    .pipe(gulp.dest(destination));
});

gulp.task('js', function () {
    var source = './custom/js/';
    var destination = './src/js/';

    return gulp.src([source + 'libs/*.js', source + '_t_*.js'])
    .pipe(plugins.concat('./t_main.js'))
    .pipe(gulp.dest(destination + './'))
    .pipe(plugins.uglify())
    .pipe(plugins.rename({suffix: '.min'}))
    .pipe(gulp.dest(destination));
});