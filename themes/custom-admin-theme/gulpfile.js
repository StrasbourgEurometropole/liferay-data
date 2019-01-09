'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var runSequence = require('run-sequence');
var svgstore = require('./lib/svgstore');
var _ = require('./lib/lodash_utils');
var del = require('del');

var plugins = require('gulp-load-plugins')();

liferayThemeTasks.registerTasks({
    gulp: gulp
});


gulp.task('build:svg:delete', function() {
   return del(['./build/images/lexicon/icons.svg']);
});

gulp.task('build:svg', function() {
   return svgstore(gulp, plugins, _, {
       dest: './build/images/lexicon/',
       src: './build/images/lexicon/*.svg',
       targetName: 'icons.svg'
   });
});

gulp.task('originalBuild', gulp.tasks['build'].dep, gulp.tasks['build'].fn);

gulp.task('build', function(cb) {
   runSequence(
       'originalBuild',
       'build:svg:delete',
       'build:svg',
       'build:war',
       cb
   );
});

