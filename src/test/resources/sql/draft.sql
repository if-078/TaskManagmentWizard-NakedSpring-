SELECT task.id, task.name, task.created_date, task.start_date, task.end_date, task.estimate_time,
  task.parent_id, task.status_id, status.name as statusName,
  task.priority_id, priority.name as priorityName,
  task.assign_to, user.name as userName
FROM task
  LEFT JOIN priority ON task.priority_id = priority.id
  LEFT JOIN status ON task.status_id = status.id
  LEFT JOIN user ON user.id=task.assign_to
WHERE task.assign_to=2 ;

SELECT tag.id, tag.name, tag.user_id FROM tag
JOIN tags_tasks ON tag.id = tags_tasks.tag_id
WHERE tags_tasks.task_id=1;