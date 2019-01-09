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
var cleancss = require('gulp-cleancss');
const uglify = require('gulp-uglify');

liferayThemeTasks.registerTasks({
	gulp: gulp
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
    .pipe(cleancss({keepBreaks: false}))
    .pipe(plugins.autoprefixer())  
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
        .pipe(uglify())
        .pipe(gulp.dest('src/js/'));
});
