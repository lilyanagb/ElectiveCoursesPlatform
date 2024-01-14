insert into role values (1, 'student');
insert into role values (2, 'teacher');
-- Password is 'pass'
insert into user values (1, 'nkrangelov', '$2y$08$slUYM03SjXBU.V5Pci1d2.9qfyHCr4I/JMC2fp.CVGq7L3t.QFpeq', 'nkrangelov@gmail.com', 1);
insert into user values (2, 'msavov', '$2y$08$slUYM03SjXBU.V5Pci1d2.9qfyHCr4I/JMC2fp.CVGq7L3t.QFpeq', 'msavov@gmail.com', 2);
insert into course values (1, 'Cloud Startup', 'ОКН', 6);
insert into course values (2, 'Operating Systems', 'ОКН', 8);
insert into course values (3, 'Introduction to Rust', 'ОКН', 5);
insert into course values (4, 'Statistics', 'ПМ/Стат', 9);
insert into enrollment_type values (1, 'student');
insert into enrollment_type values (2, 'assistant');
insert into enrollment_type values (3, 'teacher');
insert into enrollment values (1, 1, 2, 1);
insert into enrollment values (2, 1, 3, 1);
insert into enrollment values (3, 2, 4, 2);
insert into enrollment values (4, 1, 4, 1);