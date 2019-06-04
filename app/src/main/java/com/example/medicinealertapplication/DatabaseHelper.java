package com.example.medicinealertapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context myContext;
    public static final String DATABASE_NAME = "projectdatabase.db";

    private static final String tableAllUSER = "CREATE TABLE users ("+
            "idUSER INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT, "+
            "password TEXT, "+
            "morning TEXT, "+
            "afternoon TEXT, "+
            "evening TEXT"+");";

    private static final String tableAllMED = "CREATE TABLE allmed_list ("+
            "idALLMED INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "name_med TEXT," +
            "info_med TEXT," +
            "howto_med TEXT," +
            "time_med TEXT" +");";

    private static final String tableYourMED = "CREATE TABLE you_med ("+
            "idYOUMED INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "med_name TEXT," +
            "med_info TEXT," +
            "med_time TEXT," +
            "userIDMed INTEGER"+");";

    private static final String tableTimerMED = "CREATE TABLE timer_med (" +
            "idTIMERMED INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "timemed_name TEXT," +
            "timenote_med TEXT," +
            "timeinfo_med TEXT," +
            "timeeat_med TEXT," +
            "time TEXT," +
            "userIDTime INTEGER"+");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tableAllUSER);
        sqLiteDatabase.execSQL(tableAllMED);
        String Data1 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Smecta','แก้ท้องเสีย เป็นสารดูดซับทางลำไส้ สามารถเคลือบเยื่อบุเมือกของทางเดินอาหารได้'" +
                ",'ควรทานหลังอาหาร ผสมน้ำ 50 มิลลิลิตรหรืออาหารเหลว 3 กรัมไม่ควรทานพร้อมกับยาตัวอื่นๆ\nผู้ใหญ่ 1 ซองวันละ 3-4 ครั้ง\nเด็ก <1 ปีวันละ 2 ซอง 3 วันแล้วลดเหลือวันละ 1 ซอง\nเด็ก >1 ปีวันละ 4 ซอง 3 วันแล้วลดเหลือวันละ 2 ซอง','02');";
        String Data2 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Bioflor','แก้ท้องเสีย ช่วยปรับสมดุลของจุลินทรีย์ในทางเดินอาหาร ต้านแบคทีเรียที่ไม่ดีและสามารถสังเคราะห์วิตามินบีได้'" +
                ",'ควรทานหลังอาหาร 1-2 ซอง วันละ 1-2 ครั้งและตัวยาควรผสมน้ำเปล่าหรือน้ำหวาน \n**ห้ามผสมน้ำร้อน**','02');";
        String Data3 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Dehecta','เป็นยาน้ำแขวนตะกอนขนาด 3 กรัมปริมาตร 20 มิลลิลิตรแก้ท้องเสียเป็นสารดูดซับทางลำไส้ สามารถเคลือบเยื่อบุเมือกของทางเดินอาหารได้'" +
                ",'ควรทานผสมน้ำ 50 มิลลิกรัม หรืออาหารเหลว 3 กรัม และทานหลังมื้ออาหาร ไม่ควรทานพร้อมกับยาตัวอื่นๆ\nผู้ใหญ่ 1 ซองวันละ 3-4 ครั้ง\nเด็ก <1 ปีวันละ 2 ซอง 3 วันแล้วลดเหลือวันละ 1 ซอง\nเด็ก >1 ปีขึ้นไปวันละ 4 ซอง 3 วันแล้วลดเหลือวันละ 2 ซอง','02');";
        String Data4 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Infloran','ปรับความสมดุลของจุลินทรีย์ในลำไส้ ซึ่งควรรับประทานในปริมาณที่พอเหมาะจะช่วยส่งเสริมสุขภาพของร่างกาย','รับประทาน 1 แคปซูลหลังอาหารวันละสามครั้งต่อเนื่องประมาณ 3-5 วัน','02');";
        String Data5 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Domperidone','บรรเทาอาการคลื่นไส้ อาเจียน และอาการจากอาหารไม่ย่อย','ควรทานก่อนมื้ออาหาร 30 นาทีและไม่ควรใช้ยานี้ติดต่อกันเกิน 7 วัน\nผู้ใหญ่ รับประทานยาครั้งละ 10-20 มิลลิกรัม วันละ 3 ครั้ง ห้ามรับประทานยานี้เกินกว่า 80 มิลลิกรัม/วัน\nทารกและเด็กไม่ควรรับประทานยานี้','01');";
        String Data6 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Buscopan','ใช้เป็นยาแก้อาการปวดเกร็งหรือบิดเกร็งของอวัยวะในช่องท้อง เช่น ท้องเดิน ท้องเสีย หรืออุจจาระร่วง ปวดประจำเดือน เป็นต้น','ควรทานเมื่อมีอาการและรับประทานยาซ้ำได้ทุก ๆ 6-8 ชั่วโมง (ไม่เกินวันละ 4 ครั้ง ในผู้ใหญ่ และไม่เกินวันละ 3 ครั้ง ในเด็ก)','05');";
        String Data7 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Chlorpheniramine','อยู่ในกลุ่มยาแก้แพ้ บรรเทาอาการจากหวัด จาม น้ำมูกไหล น้ำตาไหล อาการคัน รวมถึงไข้หวัดใหญ่','ควรทานเมื่อมีอาการ\nเด็ก 2-5 ปี ปริมาณ 1 มิลลิกรัม ทุก 4-6 ชั่วโมง(ไม่เกิน 6 มิลลิกรัม/วัน)\nเด็ก 6-11 ปี ปริมาณ 2 มิลลิกรัมทุก 4-6 ชั่วโมง(ไม่เกิน 16 มิลลิกรัม/วัน)\nเด็ก 12 ปีขึ้นไปและผู้ใหญ่ทานปริมาณ 4 มิลลิกรัม ทุก 4-6 ชั่วโมง(ไม่เกิน 32 มิลลิกรัม/วัน)','04');";
        String Data8 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Bayer Aspirin','บรรเทาอาการปวด ลดไข้ ลดการอักเสบ เช่น ปวดศีรษะ ปวดฟัน ปวดประจำเดือน เป็นไข้ เป็นต้น','ควรรับประทานเมื่อมีอาการ\nผู้ใหญ่ รับประทานยาขนาด 325-650 มิลลิกรัม ทุก 4-6 ชั่วโมง สูงสุดไม่เกิน 4 กรัมต่อวัน','04');";
        String Data9 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Ergotamine','รักษาอาการปวดศีรษะจากไมเกรน','ควรทานเมื่อมีอาการ\nเด็กอายุ 6-12 ปี ทานครั้งแรก1 มิลลิกรัมเพิ่มปริมาณครั้งละ 1 มิลลิกรัม ทุก 30 นาทีจนทุเลา(ปริมาณสูงสุดไม่เกิน 3 มิลลิกรัม/วัน)\nผู้ใหญ่ ทานครั้งแรก 2 มิลลิกรัมเพิ่มปริมาณครั้งละ 1 มิลลิกรัม ทุก 30 นาทีจนทุเลา (ปริมาณสูงสุดไม่เกิน 6 มิลลิกรัม/วัน)','04');";
        String Data10 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Diphenhydramine','ใช้รักษาโรคภูมิแพ้ ไข้ละอองฟาง หรือไข้หวัด ช่วยยับยั้งไม่ให้เกิดอาการแพ้ต่างๆเช่น ไอ จาม น้ำมูกไหล ตาแดง ลมพิษ ผื่นผิวหนัง บรรเทาอาการเมารถยานพาหนะ','ควรทานเมื่อมีอาการ\nผู้ใหญ่ควรทานขนาด 25-50 มิลลิกรัม 3-4 ครั้งต่อวัน\nเด็กอายุ 2-6 ปี ควรทานขนาด 6.25 มิลลิกรัมและเด็กอายุ 6-12 ปี ควรทานขนาด 12.5-25 มิลลิกรัม ทุก 4-6 ชั่วโมง\nสำหรับอาการเมายานพาหนะควรทานก่อนการเดินทาง 30 นาที','04');";
        String Data11 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Loratadine','บรรเทาอาการคัน ผื่นแดง จาม น้ำมูกไหล รวมทั้งอาการจากหวัดและภูมิแพ้ทั้งหลาย','ควรทานเมื่อมีอาการ ปกติยานี้ใช้ทานเพียงวันละหนึ่งครั้งเท่านั้นและควรใช้ยาในปริมาณและระยะเวลาตามฉลากกำกับหรือแพทย์สั่ง \nเด็กอายุ 2-5 ปี (ยาน้ำเชื่อม) 5 มิลลิกรัม\nเด็กอายุมากกว่า 6 ปีและผู้ใหญ่ (ยาเม็ด แคปซูล ยาละลายในช่องปาก) 10 มิลลิกรัมหรือ 5 มิลลิกรัมทุก 12 ชั่วโมง','06');";
        String Data12 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Bisovol','ช่วยละลายเสมหะในระบบทางเดินหายใจ บรรเทาอาการไอจากโรคหวัด ไอมีเสมหะ','ควรทานพร้อมอาหารหรือหลังอาหาร\nผู้ใหญ่และเด็กอายุมากกว่า 12 ควรทานในปริมาณ 8-16 มิลลิกรัมวันละ 3 ครั้ง\nเด็กอายุ 2-5 ปี ควรทานในปริมาณ 8 มิลลิกรัม ทานวันละ 2-3 ครั้งต่อวัน \nอายุ 6-11 ปี ควรทานในปริมาณ 4-8 มิลลิกรัม ทานวันละ 3 ครั้ง','02');";
        String Data13 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Diazepam','ใช้เพื่อคลายความวิตกกังวล ปัญหานอนไม่หลับและเพื่อรักษาผู้ป่วยโรควิตกกังวล รักษาอาการตกใจกลัวอย่างรุนแรงและใช้ร่วมกับยาตัวอื่นเพื่อรักษาภาวะชักได้ด้วย','ขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคที่รักษาและอายุของผู้ป่วย\nการรักษาอาการนอนไม่หลับ\nผู้ใหญ่ ยารับประทาน 5-15 มิลลิกรัม ก่อนนอน\nเด็ก 1-2.5 มิลลิกรัม 3-4 ครั้ง/วัน','03');";
        String Data14 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Valium Diazepam','ใช้เป็นยากล่อมประสาท ช่วยผ่อนคลายความวิตกกังวล ลดความตึงเครียด รวมทั้งใช้รักษาโรคที่เกิดจากความเครียดต่างๆ ','ควรทานยาก่อนนอนครึ่งชั่วโมง ขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคที่รักษาและอายุของผู้ป่วย\nใช้เป็นยาช่วยให้นอนหลับ \nผู้ใหญ่ให้ใช้ยาครั้งละ 5-20 มิลลิกรัม \nเด็กให้ใช้ครั้งละ 2-10 มิลลิกรัม','03');";
        String Data15 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Fluoxetine','รักษาอาการจากภาวะซึมเศร้า โรคตื่นตะหนก โรคย้ำคิดย้ำทำ โรคบูลิเมียและกลุ่มอาการอารมณ์ผิดปกติก่อนมีประจำเดือน','ควรทานเมื่อมีอาการ\nผู้ใหญ่ควรทานครั้งละ 20 มิลลิกรัม วันละครั้งและสามารถเพิ่มปริมาณยาที่ใช้หากอาการยังไม่ดีขึ้นไม่เกิน 80 มิลลิกรัม/วัน\nเด็กอายุ 8-18 ปี ควรทานครั้งละ 10 มิลลิกรัม วันละครั้งหลังจากผ่านไป 1-2 สัปดาห์อาจค่อยๆเพิ่มปริมาณเป็น 20 มิลลิกรัม/วัน','04');";
        String Data16 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Zoloft','ใช้ยานี้ในการรักษาโรคซึมเศร้า โรคย้ำคิดย้ำทำ โรคแพนิกหรือโรคตื่นตระหนก โรคเครียดจากเหตุการณ์ร้ายแรง','ควรทานเมื่อมีอาการและขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคและอายุของผู้ป่วย\nโรคซึมเศร้าหรือโรคย้ำคิดย้ำทำ\nผู้ใหญ่และเด็กอายุ 13-17 ปี รับประทาน 50 มิลลิกรัม วันละ 1 ครั้ง\nเด็กอายุ 6-12 ปี ควรทาน 25 มิลลิกรัมวันละ 1 ครั้ง','03');";
        String Data17 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Amitriptyline','เป็นยาใช้รักษาอาการจากโรคซึมเศร้า','ควรทานก่อนนอนและขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคและอายุของผู้ป่วย\nเด็กอายุ 12 ปีขึ้นไปเริ่มต้น 25-50 มิลลิกรัม วันละครั้งก่อนนอน\nผู้ใหญ่เริ่มต้น 50-75 มิลลิกรัม วันละครั้งก่อนนอน\nผู้สูงอายุ เริ่มต้น 25-50 มิลลิกรัม วันละครั้งก่อนนอน','03');";
        String Data18 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Nortriptyline','เป็นยารักษาโรคซึมเศร้าในกลุ่มไตรไซคลิก ออกฤทธิ์โดยปรับสมดุลสารเคมีในสมองของผู้ป่วยโรคซึมเศร้าให้กลับสู่ภาวะปกติ','ควรทานเมื่อมีอาการ\nู้ใหญ่ รับประทานวันละ 75-100 มิลลิกรัม โดยแบ่งเป็น 3-4 ครั้ง หากมีอาการรุนแรงอาจเพิ่มปริมาณยาได้แต่ไม่เกินวันละ 150 มิลลิกรัม\n   ผู้สูงอายุและเด็กวัยรุ่น รับประทานวันละ 30-50 มิลลิกรัมโดยแบ่งเป็น 3-4 ครั้ง','04');";
        String Data19 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Furosemide','ช่วยขับของเหลวส่วนเกินในร่างกายออกมาทางปัสสาวะและช่วยป้องกันร่างกายไม่ให้ดูดซึมโซเดียมมากจนเกินไป นอกจากนี้ยังใช้ลดอาการบวมน้ำซึ่งเป็นหนึ่งในอาการของภาวะหัวใจวาย โรคตับ โรคไตและโรคไตรั่ว รวมถึงรักษาภาวะความดันโลหิตสูง ยาชนิดนี้เป็นยาที่ต้องใช้ภายใต้คำสั่งของแพทย์เท่านั้น','ทานได้ทั้งก่อนและหลังอาหาร ขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคและอายุของผู้ป่วย \nรักษาอาการบวมน้ำ\nผู้ใหญ่ควรทานวันละ 40 มิลลิกรัม ผู้สูงอายุควรทาน 20 มิลลิกรัม\nรักษาความดันโลหิตสูง\nผู้ใหญ่ 40-80 มิลลิกรัมต่อวัน สามารถใช้คู่กับยารักษาความดันโลหิตสูงได้','01');";
        String Data20 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Carbamazepine','เป็นยาในกลุ่มยากันชัก ออกฤทธิ์โดยลดการนำไฟฟ้าที่ผิดปกติของระบบประสาทและสมองอันเป็นสาเหตุให้เกิดอาการชักและอาการปวด','ควรทานเมื่อมีอาการและขนาดยาที่ใช้ขึ้นอยู่กับโรคและอายุของผู้ป่วย\nผู้ใหญ่เริ่มต้นทานยาขนาด 100-200 มิลลิกรัมวันละ 1 ครั้งหรือแบ่งทานวันละ 2 ครั้งและค่อยๆเพิ่มขนาดยาแต่ไม่เกิน 200 มิลลิกรัมต่อวันจากนั้นทานยาแบบต่อเนื่องที่ขนาด 800-1200 มิลลิกรัม สูงสุดไม่เกินวันละ 2 กรัม \nเด็กรับประทานยาขนาด 10-20 มิลลิกรัมต่อวันต่อน้ำหนักตัว 1 กิโลกรัม','04');";
        String Data21 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Paracetamol','เป็นยาที่ใช้เพื่อบรรเทาอาการปวดและช่วยลดไข้ โดยนิยมใช้เพื่อรักษาอาการปวดทั่วไป อาการปวดศีรษะ หรือไข้หวัดใหญ่ บรรเทาอาการปวดของโรคข้ออักเสบได้ ยาชนิดนี้จัดเป็นยาสามัญประจำบ้านเพราะสามารถใช้ได้โดยไม่ต้องมีใบสั่งยาของแพทย์แต่ต้องใช้ในปริมาณที่เหมาะสม','ควรทานเมื่อมีอาการ\nเด็ก 10-15 มิลลิกรัม/กิโลกรัม ทุก 4-6 ชั่วโมง(หากจำเป็น)ไม่เกิน 5 ครั้งภายใน 24 ชั่วโมง\nผู้ใหญ่ 500 มิลลิกรัม ทุก 4-6 ชั่วโมงไม่เกิน 4,000 มิลลิกรัม ต่อวัน','04');";
        String Data22 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Sara','เป็นยาที่ใช้เพื่อบรรเทาอาการปวดและช่วยลดไข้ โดยนิยมใช้เพื่อรักษาอาการปวดทั่วไป อาการปวดศีรษะ หรือไข้หวัดใหญ่ บรรเทาอาการปวดของโรคข้ออักเสบได้ ยาชนิดนี้จัดเป็นยาสามัญประจำบ้านเพราะสามารถใช้ได้โดยไม่ต้องมีใบสั่งยาของแพทย์แต่ต้องใช้ในปริมาณที่เหมาะสม','ควรทานเมื่อมีอาการ\nเด็ก 10-15 มิลลิกรัม/กิโลกรัม ทุก 4-6 ชั่วโมง(หากจำเป็น)ไม่เกิน 5 ครั้งภายใน 24 ชั่วโมง\nผู้ใหญ่ 500 มิลลิกรัม ทุก 4-6 ชั่วโมงไม่เกิน 4,000 มิลลิกรัม ต่อวัน','04');";
        String Data23 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Paracap','เป็นยาที่ใช้เพื่อบรรเทาอาการปวดและช่วยลดไข้ โดยนิยมใช้เพื่อรักษาอาการปวดทั่วไป อาการปวดศีรษะ หรือไข้หวัดใหญ่ บรรเทาอาการปวดของโรคข้ออักเสบได้ ยาชนิดนี้จัดเป็นยาสามัญประจำบ้านเพราะสามารถใช้ได้โดยไม่ต้องมีใบสั่งยาของแพทย์แต่ต้องใช้ในปริมาณที่เหมาะสม','ควรทานเมื่อมีอาการ\nเด็ก 10-15 มิลลิกรัม/กิโลกรัม ทุก 4-6 ชั่วโมง(หากจำเป็น)ไม่เกิน 5 ครั้งภายใน 24 ชั่วโมง\nผู้ใหญ่ 500 มิลลิกรัม ทุก 4-6 ชั่วโมงไม่เกิน 4,000 มิลลิกรัม ต่อวัน','04');";
        String Data24 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Tylenol','เป็นยาที่ใช้เพื่อบรรเทาอาการปวดและช่วยลดไข้ โดยนิยมใช้เพื่อรักษาอาการปวดทั่วไป อาการปวดศีรษะ หรือไข้หวัดใหญ่ บรรเทาอาการปวดของโรคข้ออักเสบได้ ยาชนิดนี้จัดเป็นยาสามัญประจำบ้านเพราะสามารถใช้ได้โดยไม่ต้องมีใบสั่งยาของแพทย์แต่ต้องใช้ในปริมาณที่เหมาะสม','ควรทานเมื่อมีอาการ\nเด็ก 10-15 มิลลิกรัม/กิโลกรัม ทุก 4-6 ชั่วโมง(หากจำเป็น)ไม่เกิน 5 ครั้งภายใน 24 ชั่วโมง\nผู้ใหญ่ 500 มิลลิกรัม ทุก 4-6 ชั่วโมง ไม่เกิน 4,000 มิลลิกรัม ต่อวัน','04');";
        String Data25 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Decolgen','บรรเทาหวัด น้ำมูกไหล ปวดศีรษะ และเป็นไข้','ควรทานเมื่อมีอาการ\nผู้ใหญ่รับประทานครั้ง 1-2 เม็ด ทุก 6-8 ชั่วโมง','05');";
        String Data26 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Solmax','ยาละลายเสมหะเพื่อบรรเทาอาการไอเนื่องจากหลอดลมอักเสบเรื้อรัง หืด หลอดลมและถุงลมพอง จากการไอที่เกิดจากโพรงจมูกอักเสบ','ผู้ใหญ่และเด็กอายุ 12 ปีขึ้นไป ครั้งละ 1 แคปซูลวันละ 3 ครั้ง(หรือตามแพทย์สั่ง)','02');";
        String Data27 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Kremil','บรรเทาอาการแผลในกระเพาะอาหาร ท้องอืด แน่น จุกเสียด หรือกรดไหลย้อน','บรรเทาอาการแผลในกระเพาะอาหาร \nครั้งละ 2-4 เม็ด ทุก 4 ชั่วโมง หรือตามแพทย์สั่ง\nลดกรดและขับลมในกระเพาะอาหาร \nครั้งละ 1-2 เม็ด หลังอาหารโดยที่เคี้ยวหรือไม่ต้องเคี้ยวก็ได้','02');";
        String Data28 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Tiffy Dey','บรรเทาอาการคัดจมูก น้ำมูกไหล น้ำตาไหล คันคอ จาม ปวดศีรษะ และเป็นไข้ อันเนื่องมาจากโรคภูมิแพ้ โรคหวัดหรือไข้ละอองฟาง','ควรทานเมื่อมีอาการ\nผู้ใหญ่ให้รับประทานยาครั้งละ 1-2 เม็ดทุก 4-6 ชั่วโมง \nเด็กอายุ 6-12 ปีให้รับประทานยาครั้งละ 1 เม็ดทุก 4-6 ชั่วโมง','04');";
        String Data29 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Zyrtec','ใช้รักษาอาการแพ้ต่างๆ บรรเทาอาการคันและอาการที่เกิดจากลมพิษ ช่วยรักษาอาการไข้หวัด ลดน้ำมูก','ผู้ใหญ่และเด็กอายุ 6 ปีขึ้นไปทานวันละครั้ง\nเด็กอายุ 2-6 ปีครึ่งเม็ดวันละครั้ง','03');";
        String Data30 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Ponstan','บรรเทาอาการปวดประจำเดือน และกลุ่มอาการผิดปกติก่อนมีประจำเดือน ลดอาการอักเสบ','ควรทานเมื่อมีอาการ ไม่ควรรับประทานยาติดต่อกันเกิน 7 วัน ผู้ใหญ่และผู้ที่มีอายุ 14 ปีขึ้นไปครั้งแรกให้รับประทานยา 500 มิลลิกรัม ต่อไปให้รับประทานยาครั้งละ 250 มิลลิกรัมโดยซ้ำได้ทุก 6 ชั่วโมงเมื่อมีอาการและสามารถหยุดรับประทานยาได้เมื่อหายจากอาการปวด ','05');";
        String Data31 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Disento','ปรับความสมดุลของจุลินทรีย์ในลำไส้ ซึ่งควรรับประทานในปริมาณที่พอเหมาะจะช่วยส่งเสริมสุขภาพของร่างกาย','ควรทานเมื่อมีอาการ รับประทานครั้งละ 1-2 เม็ด ุกๆ 4-6 ชั่วโมง หรือตามแพทย์สั่ง','04');";
        String Data32 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('CA-R-BON','ช่วยดูดซับสารพิษหรือยาบางชนิดจากการรับประทานสารพิษหรือยานั้นๆเกินขนาดช่วยรักษาอาการท้องเสียหรือถ่ายเหลวจากภาวะอาหารเป็นพิษ ช่วยแก้อาการท้องอืด ท้องเฟ้อ มีแก๊สในกระเพาะอาหาร','ควรทานเมื่อมีอาการ','04');";
        String Data33 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Krataibin','ทำลายเชื้อโรคในลำไส้ รักษาอาการอักเสบของลำไส้ แก้ปวดท้อง แก้ท้องเสีย (อาการท้องเสียจากการติดเชื้อแบบไม่รุนแรง)แก้อาการท้องอืด ท้องเฟ้อ จุกเสียด แน่นท้อง ช่วยขับลมและยังช่วยเคลือบกระเพาะอาหารเพื่อควบคุมเชื้อแบคทีเรียในกระเพาะอาหาร','\nควรทานเมื่อมีอาการหรือหลังอาหาร รับประทานครั้งละ 1-2 ช้อนชาวันละ 3 ครั้งหรือทุก 4-6 ชั่วโมง','04');";
        String Data34 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Circadin','รักษาอาการนอนไม่หลับ ช่วยปรับสมดุลการนอน โดยการเพิ่มระดับฮอร์โมน Melatonin ส่งเสริมคุณภาพการนอนหลับให้ดีขึ้น','ควรทาน 2 มิลลิกรัมวันละครั้ง 1-2 ชั่วโมงก่อนนอนและหลังอาหาร(แนะนำให้ทานต่อเนื่องเป็นเวลา 13 สัปดาห์) \nไม่แนะนำ ให้ใช้ยาในผู้ป่วยเด็กหรือวัยรุ่นเนื่องจากข้อมูลด้านความปลอดภัยและประสิทธิผลไม่เพียงพอ','03');";
        String Data35 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Cozaar','เป็นยาที่ใช้ในการรักษาความดันโลหิตสูง มีสรรพคุณรักษาภาวะความดันโลหิตสูงและโรคหลอดเลือดสมองตีบตัน ออกฤทธิ์โดยทำให้หลอดเลือดเกิดการขยายตัว','ควรทานเมื่อมีอาการและขนาดยาที่ใช้ในการรักษาขึ้นอยู่กับโรคและอายุของผู้ป่วย\nโรคความดันโลหิตสูงและโรคไตเนื่องจากโรคเบาหวาน\nผู้ใหญ่ควรทานขนาด 50 มิลลิกรัม วันละครั้ง เพิ่มได้ถึง 100 มิลลิกรัมแต่แบ่งให้วันละ1-2ครั้ง \nผู้สูงอายุมากกว่า 75 ปีเริ่มต้นควรทาน 25 มิลลิกรัมวันละครั้ง','06');";
        String Data36 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Forlax','ไฟเบอร์สำหรับเพิ่มกากใยอาหาร บรรเทาอาการท้องผูก ช่วยให้ขับถ่ายง่ายขึ้น','ควรทานเมื่อมีอาการท้องผูก','04');";
        String Data37 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Ganaton','ทำหน้าที่กระตุ้นการเคลื่อนไหวของระบบทางเดินอาหาร โดยเพิ่มการบีบตัวของกระเพาะอาหารและลำไส้','ควรทานก่อนอาหาร ผู้ใหญ่รับประทานครั้งละ 50 มิลลิกรัมวันละ 3 ครั้ง ทั้งนี้แพทย์สามารถลดขนาดรับประทานลงโดยพิจารณาจากอายุและจากอาการป่วยของผู้ป่วย ระยะเวลาของการใช้ยานี้อาจยาวนานถึง 8 สัปดาห์ขึ้นอยู่กับการตอบสนองของผู้ป่วยและดุลพินิจของแพทย์ผู้รักษา','01');";
        String Data38 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Benda 500','ยาฆ่าพยาธิสามารถออกฤทธิ์ฆ่าพยาธิและถ่ายพยาธิได้หลายชนิดภายในเวลาเดียวกัน','ให้เคี้ยวยาก่อนกลืน รับประทานครั้งละ 1 เม็ด เพียงครั้งเดียว','03');";
        String Data39 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Air-X','ใช้รักษาอาการท้องอืด ท้องเฟ้อ เนื่องจากแก๊สที่เกิดขึ้นในกระเพาะอาหารและลำไส้ บรรเทาอาการจุกเสียดเนื่องจากแก๊สที่มีปริมาณมากเกินไปและใช้กับผู้ป่วยก่อนทำการตรวจอัลตราซาวนด์ในช่องท้องเพื่อลดแก๊สที่อาจไปบดบังภาพของการตรวจอัลตราซาวนด์','ควรรับประทานครั้งละ 1-2 เม็ดเคี้ยวเม็ดยาให้ละเอียดก่อนกลืน เมื่อมีอาการท้องอืดท้องเฟ้อ สามารถรับประทานพร้อมอาหารได้','04');";
        String Data40 = "INSERT INTO allmed_list (name_med,info_med,howto_med,time_med) VALUES " +
                "('Siduol','สำหรับรักษาริดสีดวงทวารทั้งชนิดภายในและภายนอก รวมทั้งรอยแตกของริดสีดวงทวาร ตลอดจนอาการที่เกิดขึ้นร่วมด้วย เช่น มีเลือดออกและการบวมอักเสบ','ควรทานหลังอาหาร รับประทานครั้งละ 1-2 เม็ด วันละ 3 ครั้ง','02');";

        sqLiteDatabase.execSQL(Data1);
        sqLiteDatabase.execSQL(Data2);
        sqLiteDatabase.execSQL(Data3);
        sqLiteDatabase.execSQL(Data4);
        sqLiteDatabase.execSQL(Data5);
        sqLiteDatabase.execSQL(Data6);
        sqLiteDatabase.execSQL(Data7);
        sqLiteDatabase.execSQL(Data8);
        sqLiteDatabase.execSQL(Data9);
        sqLiteDatabase.execSQL(Data10);
        sqLiteDatabase.execSQL(Data11);
        sqLiteDatabase.execSQL(Data12);
        sqLiteDatabase.execSQL(Data13);
        sqLiteDatabase.execSQL(Data14);
        sqLiteDatabase.execSQL(Data15);
        sqLiteDatabase.execSQL(Data16);
        sqLiteDatabase.execSQL(Data17);
        sqLiteDatabase.execSQL(Data18);
        sqLiteDatabase.execSQL(Data19);
        sqLiteDatabase.execSQL(Data20);
        sqLiteDatabase.execSQL(Data21);
        sqLiteDatabase.execSQL(Data22);
        sqLiteDatabase.execSQL(Data23);
        sqLiteDatabase.execSQL(Data24);
        sqLiteDatabase.execSQL(Data25);
        sqLiteDatabase.execSQL(Data26);
        sqLiteDatabase.execSQL(Data27);
        sqLiteDatabase.execSQL(Data28);
        sqLiteDatabase.execSQL(Data29);
        sqLiteDatabase.execSQL(Data30);
        sqLiteDatabase.execSQL(Data31);
        sqLiteDatabase.execSQL(Data32);
        sqLiteDatabase.execSQL(Data33);
        sqLiteDatabase.execSQL(Data34);
        sqLiteDatabase.execSQL(Data35);
        sqLiteDatabase.execSQL(Data36);
        sqLiteDatabase.execSQL(Data37);
        sqLiteDatabase.execSQL(Data38);
        sqLiteDatabase.execSQL(Data39);
        sqLiteDatabase.execSQL(Data40);

        sqLiteDatabase.execSQL(tableYourMED);
//        String xData1 = "INSERT INTO you_med (medname, medinfo,userIDMed) VALUES ('" +
//                "Para','แก้ปวดหัว','10000000');";

//        sqLiteDatabase.execSQL(xData1);
//
        sqLiteDatabase.execSQL(tableTimerMED);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS allmed_list");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS you_med");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS timer_med");
        onCreate(sqLiteDatabase);
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from allmed_list ";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

}
