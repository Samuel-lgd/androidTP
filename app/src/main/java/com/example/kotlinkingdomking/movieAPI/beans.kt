package com.example.kotlinkingdomking.movieAPI


data class MovieBean(
    var id:Int,
    var name:String,
    var overview:String,
    var poster_path:String,
    var vote_average:Double,
    var popularity:Double,
)

data class MovieDetailsBean(
    var id:Int,
    var name:String,
    var overview:String,
    var poster_path:String,
    var backdrop_path:String,
    var vote_average:Double,
    var popularity:Double,
    var number_of_episodes:Int,
    var number_of_seasons:Int,
    var genres:List<GenreBean>,
    var first_air_date:String,
    var episode_run_time:List<Int>,
    var languages:List<String>,
    var origin_country:List<String>,
    var status:String,
    var seasons:List<SeasonBean>,
)

data class GenreBean(
    var id:Int,
    var name:String,
)

data class MovieSearchResults(
    var page:Int,
    var results:List<MovieBean>,
    var total_pages:Int,
    var total_results:Int
)

data class SeasonBean(
    var id:Int,
    var name:String,
    var overview:String,
    var poster_path:String,
    var season_number:Int,
    var episode_count:Int,
    var air_date:String,
)

