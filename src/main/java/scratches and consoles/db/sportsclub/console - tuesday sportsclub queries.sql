-- Opgave 1 , Find antallet af medlemmer i hvert hold
SELECT
    team_id,
    count(member_id) AS total_members
FROM
    registration
GROUP BY
    team_id;

-- Opgave 2 antaller af medlemmer for hver sportsgren
SELECT
    sport.sport,
    count(member_id) AS total_members
FROM
    team
INNER JOIN sport ON team.sport_id = sport.sport_id
INNER JOIN registration ON team.team_id = registration.team_id
GROUP BY
    sport.sport;
