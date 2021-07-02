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

gulp.task('css', function () {
    return gulp.src('./custom/scss/strasbourg.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(gulp.dest('./src/css/'));
});

gulp.task('js', function () {
    return gulp.src(['./custom/js/libs/squire.js','./custom/js/libs/jquery*.js','./custom/js/libs/*.js','./custom/js/src-1/*.js','./custom/js/**/*.js'])
        .pipe(plugins.concat('strasbourg.js'))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(gulp.dest('src/js/'));
});

gulp.task('toprod', function () {
    gulp.src(['./custom/js/libs/jquery.js','./custom/js/libs/jquery*.js','./custom/js/libs/*.js','./custom/js/src-1/*.js','./custom/js/src-2/*.js','./custom/js/src-3/*.js','./custom/js/**/*.js','./custom/js/*.js'])
        .pipe(plugins.concat('main.js'))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(gulp.dest(destination + '_js/'))
        .pipe(gulp.dest('./dist/'))
        .pipe(plugins.uglify())
        .pipe(plugins.rename({suffix: '.min'}))
        .pipe(gulp.dest('src/js'));

    gulp.src('./custom/scss/style.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(plugins.autoprefixer(autoprefixer_options))
        .pipe(gulp.dest('./src/css/'));

    return true;
});