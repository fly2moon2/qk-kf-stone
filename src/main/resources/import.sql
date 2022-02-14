-- the direct sql insert statements would not increate the h2 primary key (id) sequence.
-- put id of 1 would caused duplicate key problem when persisting hibernate obj in code
-- (by then h2 generates an id of 1 for the start)
-- hence, the static id values in the sql insert statements start with 101 instead.
Insert into wclang (id, code, lang) values (101, 'EN', 'English');
Insert into wclang (id, code, lang) values (102, 'CN', 'Chinese');

Insert into wclocale (id, code, locale, lang_id) values (101, 'en_US', 'English US',101);
Insert into wclocale (id, code, locale, lang_id) values (102, 'en_GB', 'English UK',101);
Insert into wclocale (id, code, locale, lang_id) values (103, 'zh_TW', 'Traditional Chinese',102);
Insert into wclocale (id, code, locale, lang_id) values (104, 'zh_CN', 'Simplified Chinese',102);

Insert into akprefcde (id, code, dscrp, actstatus) values (nextval('hibernate_sequence'),'WC001','Default language',1);

Insert into akpref (id, code_id, prefscope, scopesubjid, onoff, minval, maxval, parm) values (nextval('hibernate_sequence'),1,1,8086,true,null,null,'EN');

Insert into wcreltyp (id, code, reltyp, dscrp) values (101, 'ISA', 'is-a', 'Is-a relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (102, 'HSA', 'has-a', 'Has-a relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (103, 'PNT', 'parent-of', 'Parent-of relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (104, 'SBL', 'sibling-of', 'Sibling-of relationship');

insert into wcdomain (id, code, domain) values (nextval('hibernate_sequence'),'CLN','Clinical');
insert into wcdomain (id, code, domain) values (nextval('hibernate_sequence'),'TEC','Technology');
insert into wcdomain (id, code, domain) values (nextval('hibernate_sequence'),'FIN','Finance');

