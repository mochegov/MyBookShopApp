package com.example.MyBookShopApp.data.enums;

public enum BookFileType {
    PDF(".pdf"),
    FB2(".fb2"),
    EPUB(".epub");

    private final String fileExtensionString;

    BookFileType(String fileExtensionString) {
        this.fileExtensionString = fileExtensionString;
    }

    public static String getExtensionStringByTypeId(Integer typeId){
        switch (typeId){
            case 1: return BookFileType.PDF.fileExtensionString;
            case 2: return BookFileType.FB2.fileExtensionString;
            case 3: return BookFileType.EPUB.fileExtensionString;
            default: return "";
        }
    }
}
