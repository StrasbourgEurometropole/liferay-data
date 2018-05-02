var gulp = require('gulp');
var plugins = require('gulp-load-plugins')();

var source = './';
var destination = './';

var autoprefixer_options = {
    browsers: ['> 1%','last 4 versions','ios > 7','android > 4','chrome > 18','ff > 10','opera > 1','safari > 5', 'ie > 7']
};

gulp.task('css', function () {
    return gulp.src('./scss/style.scss')
        .pipe(plugins.sass({outputStyle: 'compressed'}))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(plugins.autoprefixer(autoprefixer_options))
        .pipe(gulp.dest('./dist/'));
});

gulp.task('js', function () {
    return gulp.src(['./js/libs/jquery.js','./js/libs/jquery*.js','./js/libs/*.js','./js/src-1/*.js','./js/src-2/*.js','./js/src-3/*.js','./js/**/*.js','./js/*.js'])
        .pipe(plugins.concat('main.js'))
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
        .pipe(gulp.dest('./dist/'));
    //.pipe(plugins.uglify())
    //.pipe(plugins.rename({suffix: '.min'}))
    //.pipe(gulp.dest('./dist/'));
});

gulp.task('toprod', function () {
    gulp.src(['./js/libs/jquery.js','./js/libs/jquery*.js','./js/libs/*.js','./js/src-1/*.js','./js/src-2/*.js','./js/src-3/*.js','./js/**/*.js','./js/*.js'])
        .pipe(plugins.concat('main.js'))
        .on('error', function (err) {
            console.log(err.toString());
            this.emit('end');
        })
        .pipe(gulp.dest(destination + '_js/'))
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

gulp.task('watch', function () {
    gulp.watch('./scss/**/*.scss', ['css']);
    gulp.watch(['./js/**/*.js','./js/*.js'], ['js']);
});

gulp.task('default', ['watch']);
