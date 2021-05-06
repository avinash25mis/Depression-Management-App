insert into day_wise_content_file_list values (1,1);

INSERT INTO public.user_role (user_role_id, role_code, role_name) VALUES (1, 'ADMIN', 'ADMIN');
INSERT INTO public.user_role (user_role_id, role_code, role_name) VALUES (2, 'VIEW', 'VIEW');


INSERT INTO public.app_user (id, active, email, first_name, gender, last_name, password, username) VALUES (1, true, NULL, NULL, NULL, NULL, 'sam', 'sam');

INSERT INTO app_user_user_role(app_user_id, user_role_user_role_id) VALUES (1, 1);
INSERT INTO app_user_user_role(app_user_id, user_role_user_role_id) VALUES (1, 2);
