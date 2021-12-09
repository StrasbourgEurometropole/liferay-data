'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
var runSequence = require('run-sequence');
var svgstore = require('./lib/svgstore');
var _ = require('./lib/lodash_utils');
var del = require('del');

var plugins = require('gulp-load-plugins')();
var del = require('del');
var runSequence = require('run-sequence').use(gulp);

liferayThemeTasks.registerTasks({
  gulp: gulp,
  hookFn: function(gulp) {
    gulp.task('build:r2', function(done) {
      const plugins = require('gulp-load-plugins')();
  
      return gulp
        .src(['./build/css/*.css','!./build/css/*_rtl.css'])
    });

    gulp.hook('after:build:move-compiled-css', function(done) {
        runSequence('remove-maps', 'remove-scss', done);
    })
  }
});

gulp.task('remove-maps', cb => {
	del('./build/**/*.map').then(() => cb());
});

gulp.task('remove-scss', cb => {
	del('./build/**/*.scss').then(() => cb());
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

