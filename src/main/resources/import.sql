Insert into wclang (id, code, lang) values (1, 'EN', 'English');
Insert into wclang (id, code, lang) values (2, 'CN', 'Chinese');

Insert into wclocale (id, code, locale, lang_id) values (1, 'en_US', 'English US',1);
Insert into wclocale (id, code, locale, lang_id) values (2, 'en_GB', 'English UK',1);
Insert into wclocale (id, code, locale, lang_id) values (3, 'zh_TW', 'Traditional Chinese',2);
Insert into wclocale (id, code, locale, lang_id) values (4, 'zh_CN', 'Simplified Chinese',2);

Insert into wcreltyp (id, code, reltyp, dscrp) values (1, 'ISA', 'is-a', 'Is-a relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (2, 'HSA', 'has-a', 'Has-a relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (3, 'PNT', 'parent-of', 'Parent-of relationship');
Insert into wcreltyp (id, code, reltyp, dscrp) values (4, 'SBL', 'sibling-of', 'Sibling-of relationship');