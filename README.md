# My Personal Project

## VALORANT Match Recorder:
A system that displays and records all of your *VALORANT* matches

**Features**:
- Everytime you play a game, you can add it to your match history to keep a record
- You can enter the details of the match (what character you played and how many kills you got)
- You can enter the result of the match (if you won or lose and by how many points)
- You can calculate your average win rate and who is your most played character based on the games 
you have added into the match history

The X class represents each match of *VALORANT*. The Y class would be a list/collection of matches (the match history).
You can add an arbitrary number of matches into the match history 

This project is designed for gamers who play *VALORANT*. This project is of interest to me as gaming is one of my 
passions. I have been gaming ever since elementary school. Through gaming, I have made lots of long-lasting
relationships and it has helped me discover my interest in computer science. As a gamer, I think this project will 
be useful to track the matches I've played and to see my improvement over time.

## User Stories:

- As a user, I want to be able to create a new match and add it to a list of previous matches 
(add it to the match history)
- As a user, I want to be able to calculate my win rate based off of all the matches I've added
- As a user, I want to be able to view all my matches I've added into the match history
- As a user, I want to be able to see and compare how many games I've played on each agent through a bar graph
- As a user, I want to have the option to save my current match history to file
- As a user, I want to have the option to load my current match history from file

## Instructions for Grader:
- You can generate the first required action related to adding Xs to a Y by going into the add tab
and typing the match details into the text fields, then pressing the button labeled "add"
- You can generate the second required action related to adding Xs to a Y by going into the winrate/agent
tab, then pressing the button labeled "calculate win rate"
- You can locate my visual component by going into the winrate/agent tab, then pressing the button labeled
"See number of games on agents"
- You can save the state of my application by going into the save/load tab, then pressing the button 
labeled "save match history"
- You can reload the state of my application by going into the save/load tab, then pressing the button
  labeled "load match history"
- You can view all the Xs added into the Y by going into the view tab, then pressing the button labeled
"Match History Status"

## Phase 4: Task 2
This is a sample event log that would be printed out for the following key events:
1. Adding a match
2. Calculating win rate
3. Viewing match history
4. Adding a match
5. Adding a match
6. Calculating win rate
7. Viewing match history  

Fri Aug 04 16:04:01 PDT 2023  
Added a match to match history (win: 13-5 as SOVA)  
Fri Aug 04 16:04:05 PDT 2023  
Win rate was calculated: 100.0%  
Fri Aug 04 16:04:07 PDT 2023  
Displayed matches in match history.  
Fri Aug 04 16:04:17 PDT 2023  
Added a match to match history (lose: 7-13 as JETT)  
Fri Aug 04 16:04:30 PDT 2023  
Added a match to match history (lose: 1-13 as PHOENIX)  
Fri Aug 04 16:04:32 PDT 2023  
Win rate was calculated: 33.33333333333333%  
Fri Aug 04 16:04:34 PDT 2023  
Displayed matches in match history.

## Phase 4: Task 3

- To try to improve my design, I would 