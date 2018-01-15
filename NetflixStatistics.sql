DROP DATABASE IF EXISTS NetflixStatistics;

CREATE DATABASE NetflixStatistics;

USE NetflixStatistics;

CREATE TABLE Subscription (
	subscriptionId int IDENTITY NOT NULL PRIMARY KEY,
	nameSubscriber nvarchar(40) NOT NULL,
	streetName nvarchar(40) NOT NULL,
	houseNumber nvarchar(5) NOT NULL,
	city nvarchar(40) NOT NULL 
);

CREATE TABLE Program (
	programId int IDENTITY NOT NULL PRIMARY KEY,
	title nvarchar(255) NOT NULL,
	duration int NOT NULL
);

CREATE TABLE UserProfile (
	profileId int IDENTITY NOT NULL PRIMARY KEY,
	subId int NOT NULL FOREIGN KEY REFERENCES Subscription(subscriptionId),
	profileName nvarchar(40) NOT NULL,
	age int NOT NULL
);

CREATE TABLE ViewBehaviour (
	viewBehaviourId int IDENTITY NOT NULL PRIMARY KEY,
	profileId int NOT NULL,
	programId int NOT NULL,
	progressPerct nvarchar(4) NOT NULL

	CONSTRAINT profileIdFK
	FOREIGN KEY (profileId)
	REFERENCES UserProfile(profileId)
	ON UPDATE CASCADE
	ON DELETE CASCADE,

	CONSTRAINT programIdFK
	FOREIGN KEY (programId)
	REFERENCES Program(programId)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE TVshow (
	tvshowId int IDENTITY NOT NULL PRIMARY KEY,
	tvshowBackUp int NOT NULL,
	title nvarchar(255) NOT NULL,
	genre nvarchar(40) NOT NULL,
	languageTVshow nvarchar(40),
	age int NOT NULL

	CONSTRAINT tvshowBackUpFK
	FOREIGN KEY (tvshowBackUp)
	REFERENCES TVshow(tvshowId)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);

CREATE TABLE Episode (
	episodeId int IDENTITY NOT NULL PRIMARY KEY,
	programId int NOT NULL,
	tvshowId int NOT NULL,
	episodeNr nvarchar(6) NOT NULL

	CONSTRAINT episodeProgramIdFK
	FOREIGN KEY (programId)
	REFERENCES Program(programId)
	ON UPDATE CASCADE
	ON DELETE CASCADE,

	CONSTRAINT tvshowIdFK
	FOREIGN KEY (tvshowId)
	REFERENCES TVshow(tvshowId)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE Film (
	filmId int IDENTITY NOT NULL PRIMARY KEY,
	programId int NOT NULL,
	genre nvarchar(40) NOT NULL,
	filmLanguage nvarchar(40) NOT NULL,
	age int NOT NULL,

	CONSTRAINT filmProgramIdFK
	FOREIGN KEY (programId)
	REFERENCES Program(programId)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- tvshow Vikings
-- Season 1
INSERT INTO Program(title, duration)
VALUES ('Rites of Passage', 45);

INSERT INTO Program(title, duration)
VALUES ('Wrath of the Notrthmen', 45);

INSERT INTO Program(title, duration)
VALUES ('Dispossessed', 45);

INSERT INTO Program(title, duration)
VALUES ('Trial', 45);

INSERT INTO Program(title, duration)
VALUES ('Raid', 44);

INSERT INTO Program(title, duration)
VALUES ('Burial of the Dead', 45);

-- End of season 1
-- Season 2

INSERT INTO Program(title, duration)
VALUES ('Brother''s War', 48);

INSERT INTO Program(title, duration)
VALUES ('Invasion', 45);

INSERT INTO Program(title, duration)
VALUES ('Treachery', 45);

-- end of tvshow Vikings
-- tvshow Peaky Blinders
-- Season 1
INSERT INTO Program(title, duration)
VALUES ('Episode #1.1', 58);

-- End of season 1
-- Season 2
INSERT INTO Program(title, duration)
VALUES ('Episode #2.1', 59);

-- End of season 2
-- Season 3
INSERT INTO Program(title, duration)
VALUES ('Episode #3.1', 56);

-- End of season 3
-- Season 4
INSERT INTO Program(title, duration)
VALUES ('The Noose', 60);

-- end of tvshow Peaky Blinders
-- tvshow Stranger Things
-- Season 1
INSERT INTO Program(title, duration)
VALUES ('Chapter One: The Vanishing of Will Bayers', 47);

INSERT INTO Program(title, duration)
VALUES ('Chapter Two: The weirdo on Maple Street', 55);

-- End of season 1
-- Season 2

INSERT INTO Program(title, duration)
VALUES ('Chapter One: MADMAX', 48);

INSERT INTO Program(title, duration)
VALUES ('Chapter Two: Trick or Treat, Freek', 56);

-- End of Season 2
-- End of tvshow Stranger Things
-- tvshow Game of Thrones
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Winter Is Comming', 62);

INSERT INTO Program(title, duration)
VALUES ('The Kingsroad', 56);

INSERT INTO Program(title, duration)
VALUES ('Lord Snow', 58);

-- End of season 1
-- Season 2

INSERT INTO Program(title, duration)
VALUES ('The North Remembers', 53);

INSERT INTO Program(title, duration)
VALUES ('The Night Lands', 54);

-- End of season 2
-- End of tvshow Game of Thrones

-- tvshow The Walking Dead
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Days Gone Bye', 67);

INSERT INTO Program(title, duration)
VALUES ('Guts', 45);

INSERT INTO Program(title, duration)
VALUES ('Tell It to the Frogs', 44);

INSERT INTO Program(title, duration)
VALUES ('Vatos', 45);

INSERT INTO Program(title, duration)
VALUES ('Wildfire', 45);

INSERT INTO Program(title, duration)
VALUES ('TS-19', 45);

-- End of season 1
-- End of tvshow The Walking Dead

-- tvshow The Crown
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Wolferton Splash', 56);

INSERT INTO Program(title, duration)
VALUES ('Hyde Park Corner', 61);

INSERT INTO Program(title, duration)
VALUES ('Windsor', 59);

INSERT INTO Program(title, duration)
VALUES ('Act of God', 57);

INSERT INTO Program(title, duration)
VALUES ('Smoke and Mirrors', 55);

INSERT INTO Program(title, duration)
VALUES ('Gelignite', 58);

INSERT INTO Program(title, duration)
VALUES ('Scientia Potentia Est', 58);

INSERT INTO Program(title, duration)
VALUES ('Pride & Joy', 58);

INSERT INTO Program(title, duration)
VALUES ('Assassins', 60);

INSERT INTO Program(title, duration)
VALUES ('Gloriana', 54);

-- End of season 1
-- End of tvshow The Crown

-- tvshow Dark
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Secrets', 51);

INSERT INTO Program(title, duration)
VALUES ('Lies', 44);

INSERT INTO Program(title, duration)
VALUES ('Past and Present', 45);

INSERT INTO Program(title, duration)
VALUES ('Double Lives', 47);

INSERT INTO Program(title, duration)
VALUES ('Truths', 45);

INSERT INTO Program(title, duration)
VALUES ('Sic Mundus Creatus Est', 51);

INSERT INTO Program(title, duration)
VALUES ('Crossroads', 52);

INSERT INTO Program(title, duration)
VALUES ('As You Sow, so You Shall Reap', 50);

INSERT INTO Program(title, duration)
VALUES ('Everything Is Now', 55);

INSERT INTO Program(title, duration)
VALUES ('Alpha and Omega', 57);

-- End of season 1
-- end of tvshow Dark

-- tvshow The Flash
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Pilot', 44);

INSERT INTO Program(title, duration)
VALUES ('Fastest Man Alive', 42);

INSERT INTO Program(title, duration)
VALUES ('Things You Can''t Outrun', 42);

INSERT INTO Program(title, duration)
VALUES ('Going Rogue', 42);

INSERT INTO Program(title, duration)
VALUES ('Plastique', 42);

-- End of season 1 (incomplete)
-- End of tvshow The Flash

-- tvshow Riverdale
-- Season 1

INSERT INTO Program(title, duration)
VALUES ('Chapter One: The River''s Edge', 46);

INSERT INTO Program(title, duration)
VALUES ('Chapter Two: A Touch of Evil', 42);

INSERT INTO Program(title, duration)
VALUES ('Chapter Three: Body Double', 41);

INSERT INTO Program(title, duration)
VALUES ('Chapter Four: The Last Picture Show', 41);

INSERT INTO Program(title, duration)
VALUES ('Chapter Five: Heart of Darkness', 41);

-- end of season 1 (incomplete)
-- end of tvshow Riverdale

INSERT INTO Program(title, duration)
VALUES ('Jumanji: Welcome to the Jungle', 119);

INSERT INTO Program(title, duration)
VALUES ('Star Wars: Episode VIII - The Last Jedi', 152);

INSERT INTO Program(title, duration)
VALUES ('The Greatest Showman', 105);

INSERT INTO Program(title, duration)
VALUES ('Blade Runner 2049', 164);

INSERT INTO Program(title, duration)
VALUES ('Dunkirk', 106);

INSERT INTO Program(title, duration)
VALUES ('IT', 135);

INSERT INTO Program(title, duration)
VALUES ('Thor: Regnarok', 130);

INSERT INTO Program(title, duration)
VALUES ('Home Alone', 103);

INSERT INTO Program(title, duration)
VALUES ('Frozen', 102);

INSERT INTO Program(title, duration)
VALUES ('Big Hero 6', 102);

INSERT INTO Program(title, duration)
VALUES ('Despicable Me 2', 98);

INSERT INTO Program(title, duration)
VALUES ('Ice Age', 81);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (1, 'Vikings','Action','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (2, 'Peaky Blinders','Crime','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (3, 'Stranger Things','Horror Fantasy','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (4, 'Game of Thrones','Adventure','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (5, 'The Walking Dead','Thriller','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (6, 'The Crown','Drama','English',9);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (7, 'Dark','Crime','English',16);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (8, 'The Flash','Action','English',12);

INSERT INTO TVshow(tvshowBackUp, title, genre, languageTVshow, age)
VALUES (9, 'Riverdale','Crime','English',12);

-- tvshow Vikings
-- Season 1
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (1, 1, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (2, 1, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (3, 1, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (4, 1, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (5, 1, 'S1E5');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (6, 1, 'S1E6');

-- End of season 1
-- Season 2

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (7, 1, 'S2E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (8, 1, 'S2E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (9, 1, 'S2E3');

-- end of tvshow Vikings
-- tvshow Peaky Blinders
-- Season 1
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (10, 2, 'S1E1');

-- End of season 1
-- Season 2
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (11, 2, 'S2E1');

-- End of season 2
-- Season 3
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (12, 2, 'S3E1');

-- End of season 3
-- Season 4
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (13, 2, 'S4E1');

-- end of tvshow Peaky Blinders
-- tvshow Stranger Things
-- Season 1
INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (14, 3, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (15, 3, 'S1E2');

-- End of season 1
-- Season 2

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (16, 3, 'S2E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (17, 3, 'S2E2');

-- End of Season 2
-- End of tvshow Stranger Things
-- tvshow Game of Thrones
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (18, 4, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (19, 4, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (20, 4, 'S1E3');

-- End of season 1
-- Season 2

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (21, 4, 'S2E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (22, 4, 'S2E2');

-- End of season 2
-- End of tvshow Game of Thrones

-- tvshow The Walking Dead
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (23, 5, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (24, 5, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (25, 5, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (26, 5, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (27, 5, 'S1E5');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (28, 5, 'S1E6');

-- End of season 1
-- End of tvshow The Walking Dead

-- tvshow The Crown
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (29, 6, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (30, 6, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (31, 6, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (32, 6, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (33, 6, 'S1E5');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (34, 6, 'S1E6');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (35, 6, 'S1E7');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (36, 6, 'S1E8');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (37, 6, 'S1E9');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (38, 6, 'S1E10');

-- End of season 1
-- End of tvshow The Crown

-- tvshow Dark
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (39, 7, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (40, 7, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (41, 7, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (42, 7, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (43, 7, 'S1E5');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (44, 7, 'S1E6');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (45, 7, 'S1E7');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (46, 7, 'S1E8');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (47, 7, 'S1E9');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (48, 7, 'S1E10');

-- End of season 1
-- end of tvshow Dark

-- tvshow The Flash
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (49, 8, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (50, 8, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (51, 8, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (52, 8, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (53, 8, 'S1E5');

-- End of season 1 (incomplete)
-- End of tvshow The Flash

-- tvshow Riverdale
-- Season 1

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (54, 9, 'S1E1');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (55, 9, 'S1E2');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (56, 9, 'S1E3');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (57, 9, 'S1E4');

INSERT INTO Episode(programId, tvshowId, episodeNr)
VALUES (58, 9, 'S1E5');

-- end of season 1 (incomplete)
-- end of tvshow Riverdale

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (59, 'Action Adventure', 'English', 12);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (60, 'Action Adventure', 'English', 12);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (61, 'Biography', 'English', 9);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (62, 'Sci-Fi', 'English', 16);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (63, 'Action, Drama', 'English', 12);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (64, 'Horror', 'English', 16);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (65, 'Action Adventure', 'English', 12);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (66, 'Comedy', 'English', 0);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (67, 'Animation', 'English', 6);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (68, 'Animation Action', 'English', 6);

INSERT INTO Film(programId, genre, filmLanguage, age)
VALUES (69, 'Animation Adventure', 'Dutch', 6);

INSERT INTO Subscription (nameSubscriber, streetName, houseNumber, city)
VALUES ('Niek', 'Populierstraat', '18', 'Breda');

INSERT INTO Subscription (nameSubscriber, streetName, houseNumber, city)
VALUES ('Sam', 'Okeghemlaan', '2', 'Breda');

INSERT INTO Subscription (nameSubscriber, streetName, houseNumber, city)
VALUES ('Arjan', 'Biestraat', '3B', 'Gilze');

INSERT INTO UserProfile (subId, profileName, age)
VALUES (1, 'Niek', 20);

INSERT INTO UserProfile (subId, profileName, age)
VALUES (2, 'Sam', 22);

INSERT INTO UserProfile (subId, profileName, age)
VALUES (3, 'Arjan', 18);

INSERT INTO UserProfile (subId, profileName, age)
VALUES (1, 'Iris', 23);

INSERT INTO UserProfile (subId, profileName, age)
VALUES (2, 'David', 28);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (1, 1, 22);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (2, 1, 89);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (3, 4, 10);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (1, 8, 90);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (2, 2, 1);

INSERT INTO ViewBehaviour (profileId, programId, progressPerct)
VALUES (3, 3, 50);