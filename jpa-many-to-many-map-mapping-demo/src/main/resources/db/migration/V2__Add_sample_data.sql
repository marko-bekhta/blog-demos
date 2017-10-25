INSERT INTO public.actor (id, uuid, first_name, last_name)
VALUES (7, '8f3c7427-e04b-4f19-ae7f-7182dac731bc', 'John', 'Doe');
INSERT INTO public.actor (id, uuid, first_name, last_name)
VALUES (8, 'b5e4f232-c935-41a0-b9e1-0f2cd446bddf', 'Jane', 'Doe');

INSERT INTO public.film (id, uuid, length, name)
VALUES (9, '84c2d729-1d86-42aa-92ef-d8824a9a2c59', 4800000000000, 'John and Jane');

INSERT INTO public.film_to_actor (film_id, actor_id, main_character, first_name, last_name)
VALUES (9, 7, TRUE, 'John', 'Brown');
INSERT INTO public.film_to_actor (film_id, actor_id, main_character, first_name, last_name)
VALUES (9, 8, TRUE, 'Jane', 'Brown');
