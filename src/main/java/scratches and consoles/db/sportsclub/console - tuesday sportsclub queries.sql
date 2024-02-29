-- Opgave 0 Laver et view til at gøre resten nemmere, hopefully nemmere
-- So that was a fucking lie
CREATE VIEW members_and_sports AS
SELECT
    m.member_id,
    m.name,
    m.address,
    z.city,
    m.gender,
    m.year,
    s.sport,
    t.team_id,
    r.price
FROM
    member m
        LEFT JOIN public.zip z ON z.zip = m.zip
        LEFT JOIN public.registration r ON m.member_id = r.member_id
        LEFT JOIN public.team t ON t.team_id = r.team_id
        LEFT JOIN public.sport s ON s.sport_id = t.sport_id


CREATE VIEW registrations_detailed AS
SELECT
    members_and_sports.member_id,
    members_and_sports.name,
    members_and_sports.address,
    members_and_sports.city,
    members_and_sports.gender,
    members_and_sports.year,
    members_and_sports.sport,
    members_and_sports.team_id,
    members_and_sports.price
FROM
    members_and_sports
WHERE
    members_and_sports.sport IS NOT NULL
    AND members_and_sports.team_id IS NOT NULL
    AND members_and_sports.price IS NOT NULL;


-- DROP VIEW members_and_sports;

SELECT *
FROM
    members_and_sports
ORDER BY
    member_id;



-- Opgave 7 , Find antallet af medlemmer i hvert hold
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





