package com.lalabib.project.mtv.utils

import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "1",
                "Free Guy",
                "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                "2021-08-11",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/acCS12FVUQ7blkC8qEbuXbsWEs2.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "2",
                "Venom: Let There Be Carnage",
                "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                "2021-09-30",
                "6.9",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/dZa00JxrwBL2oePDqcwwlJfM8qJ.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "3",
                "Shang-Chi and the Legend of the Ten Rings",
                "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
                "2021-09-01",
                "7.7",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/pS1XUGjC6ASC1kvDCP3OJnwjk1t.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "4",
                "Snake Eyes: G.I. Joe Origins",
                "After saving the life of their heir apparent, tenacious loner Snake Eyes is welcomed into an ancient Japanese clan called the Arashikage where he is taught the ways of the ninja warrior. But, when secrets from his past are revealed, Snake Eyes' honor and allegiance will be tested – even if that means losing the trust of those closest to him.",
                "2021-07-22",
                "6.9",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/uIXF0sQGXOxQhbaEaKOi2VYlIL0.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "5",
                "Venom",
                "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.",
                "2018-09-28",
                "6.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "6",
                "Coco",
                "Despite his family’s baffling generations-old ban on music, Miguel dreams of becoming an accomplished musician like his idol, Ernesto de la Cruz. Desperate to prove his talent, Miguel finds himself in the stunning and colorful Land of the Dead following a mysterious chain of events. Along the way, he meets charming trickster Hector, and together, they set off on an extraordinary journey to unlock the real story behind Miguel's family history.",
                "2017-10-27",
                "8.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/gGEsBPAijhVUFoiNpgZXqRVWJt2.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "7",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                "7.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "8",
                "Black Widow",
                "Natasha Romanoff, also known as Black Widow, confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises. Pursued by a force that will stop at nothing to bring her down, Natasha must deal with her history as a spy and the broken relationships left in her wake long before she became an Avenger.",
                "2021-07-07",
                "7.7",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/ytnhzdwtj0YfC5NVWrrPRGSGZb7.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "9",
                "F9",
                "Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
                "2021-05-19",
                "7.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/gwPAfjqVJh8tb616CRKWQriTY4N.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "10",
                "Luca",
                "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
                "2021-06-17",
                "8.0",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/jTswp6KyDYKtvC52GbHagrZbGvD.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "11",
                "The Suicide Squad",
                "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
                "2021-07-28",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/5SqXxcQWCb5X8zINltsSwPy0yKL.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "12",
                "The Boss Baby: Family Business",
                "The Templeton brothers — Tim and his Boss Baby little bro Ted — have become adults and drifted away from each other. But a new boss baby with a cutting-edge approach and a can-do attitude is about to bring them together again … and inspire a new family business.",
                "2021-07-01",
                "7.7",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/kv2Qk9MKFFQo4WQPaYta599HkJP.jpg"
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "1",
                "Alice in Borderland",
                "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
                "2020-12-10",
                "8.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/20mOwAAPwZ1vLQkw0fvuQHiG7bO.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "2",
                "Squid Game",
                "Hundreds of cash-strapped players accept a strange invitation to compete in children's games—with high stakes. But, a tempting prize awaits the victor.",
                "2021-09-17",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/uu4TgyyW259aOZHN0Ew4TEfjnUG.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "3",
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2011-04-17",
                "8.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "4",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-01-25",
                "8.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/ekZobS8isE6mA53RAiGDG93hBxL.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "5",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31",
                "8.1",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "6",
                "Sex Education",
                "Inexperienced Otis channels his sex therapist mom when he teams up with rebellious Maeve to set up an underground sex therapy clinic at school.",
                "2019-01-11",
                "8.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/8j12tohv1NBZNmpU93f47sAKBbw.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "7",
                "The Good Doctor",
                "Shaun Murphy, a young surgeon with autism and savant syndrome, relocates from a quiet country life to join a prestigious hospital's surgical unit. Unable to personally connect with those around him, Shaun uses his extraordinary medical gifts to save lives and challenge the skepticism of his colleagues.",
                "2017-09-25",
                "8.6",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/cXUqtadGsIcZDWUTrfnbDjAy8eN.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "8",
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant” or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "8.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/hZd6Z2e5Epb2xoEFKm3cv7hgiKv.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "9",
                "What If...?",
                "Taking inspiration from the comic books of the same name, each episode explores a pivotal moment from the Marvel Cinematic Universe and turns it on its head, leading the audience into uncharted territory.",
                "2021-08-11",
                "8.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/lztz5XBMG1x6Y5ubz7CxfPFsAcW.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "10",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/4Pgs2qIfzFP2RXiLA3eLN5KTByX.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "11",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "2021-01-15",
                "8.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/1BvbezZqur1zTMWd1pmtk8oV1wz.jpg"
            )
        )

        tvShow.add(
            TvShowEntity(
                "12",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_face/yZevl2vHQgmosfwUdVNzviIfaWS.jpg"
            )
        )

        return tvShow
    }

    fun getDetailMovie(id: String): MovieEntity {
        return MovieEntity(
            id,
            "Free Guy",
            "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
            "2021-08-11",
            "7.8",
            "https://www.themoviedb.org/t/p/w600_and_h900_face/acCS12FVUQ7blkC8qEbuXbsWEs2.jpg"
        )
    }

    fun getDetailTvShow(id: String): TvShowEntity {
        return TvShowEntity(
            id,
            "Alice in Borderland",
            "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
            "2020-12-10",
            "8.2",
            "https://www.themoviedb.org/t/p/w600_and_h900_face/20mOwAAPwZ1vLQkw0fvuQHiG7bO.jpg"
        )
    }
}