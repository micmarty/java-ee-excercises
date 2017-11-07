delete from LASY;
delete from ELFY;

insert into LASY (ilosc_drzew) values (10);
insert into LASY (ilosc_drzew) values (52);
insert into LASY (ilosc_drzew) values (88);

insert into ELFY (imie, liczba_strzal, rodzaj_luku, las_id) values ('xaraneee', 101, 'DREWNIANY', 1);
insert into ELFY (imie, liczba_strzal, rodzaj_luku, las_id) values ('yyyy', 220, 'DREWNIANY', 1);
insert into ELFY (imie, liczba_strzal, rodzaj_luku, las_id) values ('ciamajdelf', 3, 'DREWNIANY', 2);
insert into ELFY (imie, liczba_strzal, rodzaj_luku, las_id) values ('senny elf', 44, 'DREWNIANY', 3);

select * from ELFY;
select * from LASY;