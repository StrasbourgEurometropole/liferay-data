/**
 * © 2017 Liferay, Inc. <https://liferay.com>
 *
 * SPDX-License-Identifier: MIT
 */

'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');
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
