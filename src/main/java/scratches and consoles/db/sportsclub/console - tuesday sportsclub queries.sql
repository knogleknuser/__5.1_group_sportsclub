-- Opgave 0 Laver et view til at gøre resten nemmere, hopefully nemmere
CREATE VIEW members_and_sports AS
SELECT
    member.member_id,
    member.name,
    member.address,
    zip.city,
    member.gender,
    member.year,
    sport.sport,
    team.team_id,
    registration.price
FROM
    team
        INNER JOIN sport USING (sport_id)
        INNER JOIN registration USING (team_id)
        INNER JOIN member USING (member_id)
        INNER JOIN zip USING (zip);


SELECT *
FROM
    members_and_sports
ORDER BY
    name;


-- Opgave 17 , Find antallet af medlemmer i hvert hold
SELECT
    team_id,
    COUNT(member_id) AS total_members
FROM
    registration
GROUP BY
    team_id;

-- Opgave 8 antaller af medlemmer for hver sportsgren
SELECT
    sport.sport,
    COUNT(member_id) AS total_members
FROM
    team
        INNER JOIN sport ON team.sport_id = sport.sport_id
        INNER JOIN registration ON team.team_id = registration.team_id
GROUP BY
    sport.sport;

-- Nu finders der et view, bruger nok bare det fordi jeg er dovn, hvis det giver mening ofc

-- Opgave 9 Find the number of men and women in the club
SELECT
    member.gender,
    COUNT(member.gender) AS total_members
FROM
    member
WHERE
--     IDK why it indents more than needed for this :(
        member.member_id IN (
                            SELECT DISTINCT
                                member.member_id
                            FROM
                                member
                            )
GROUP BY
    member.gender;


-- Opgave 10 Find the total sum of income for all teams
SELECT
    SUM(registration.price) AS total_income
FROM
    registration;



-- Opgave 11 Find the total sum of income for each team
SELECT
    registration.team_id,
    SUM(registration.price) AS total_income
FROM
    registration
GROUP BY
    registration.team_id;



-- Opgave 12 Find the average payment for each team
SELECT
    registration.team_id,
    AVG(registration.price) AS avg_income_pr_member_in_team
FROM
    registration
GROUP BY
    registration.team_id;





